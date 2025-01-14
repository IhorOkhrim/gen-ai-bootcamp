package com.epam.training.gen.ai.service;

import static io.qdrant.client.PointIdFactory.id;
import static io.qdrant.client.ValueFactory.value;
import static io.qdrant.client.VectorsFactory.vectors;
import static io.qdrant.client.WithPayloadSelectorFactory.enable;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.epam.training.gen.ai.config.ClientQdrantProperties;
import com.epam.training.gen.ai.model.EmbeddingRequest;
import com.epam.training.gen.ai.model.EmbeddingResponse;
import com.epam.training.gen.ai.model.QdrantResponse;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import io.qdrant.client.grpc.Points;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class EmbeddingService {

    private final OpenAIAsyncClient openAIAsyncClient;
    private final QdrantClient qdrantClient;
    private final ClientQdrantProperties clientQdrantProperties;


    /**
     * Processes the input text into embeddings, transforms them into vector points, and saves them in the Qdrant
     * collection.
     *
     * @param text the text to be processed into embeddings
     * @throws ExecutionException   if the vector saving operation fails
     * @throws InterruptedException if the thread is interrupted during execution
     */
    public void processAndSaveText(String text) throws ExecutionException, InterruptedException {
        var embeddings = getEmbeddings(text);
        var points = new ArrayList<List<Float>>();
        embeddings.forEach(embeddingItem -> {
            var values = new ArrayList<>(embeddingItem.getEmbedding());
            points.add(values);
        });

        var pointStructs = new ArrayList<Points.PointStruct>();
        points.forEach(point -> {
            var pointStruct = getPointStruct(point, text);
            pointStructs.add(pointStruct);
        });

        saveVector(pointStructs);
    }

    /**
     * Searches the Qdrant collection for vectors similar to the input text.
     * <p>
     * The input text is converted to embeddings, and a search is performed based on the vector similarity.
     *
     * @param text the text to search for similar vectors
     * @return a list of scored points representing similar vectors
     * @throws ExecutionException   if the search operation fails
     * @throws InterruptedException if the thread is interrupted during execution
     */
    public List<Points.ScoredPoint> search(String text) throws ExecutionException, InterruptedException {
        var embeddings = retrieveEmbeddings(text);
        var qe = new ArrayList<Float>();
        Objects.requireNonNull(embeddings.block()).getData().forEach(embeddingItem -> qe.addAll(embeddingItem.getEmbedding()));
        return qdrantClient.searchAsync(
                Points.SearchPoints.newBuilder().setCollectionName(clientQdrantProperties.getCollectionName())
                        .addAllVector(qe).setWithPayload(enable(true)).setLimit(10).build()).get();
    }

    /**
     * Retrieves the embeddings for the given text using Azure OpenAI.
     *
     * @param text the text to be embedded
     * @return a list of {@link EmbeddingItem} representing the embeddings
     */
    public List<EmbeddingItem> getEmbeddings(String text) {
        var embeddings = retrieveEmbeddings(text);
        return Objects.requireNonNull(embeddings.block()).getData();
    }

    /**
     * Retrieves the embeddings for the given text asynchronously from Azure OpenAI.
     *
     * @param text the text to be embedded
     * @return a {@link Mono} of {@link Embeddings} representing the embeddings
     */
    private Mono<Embeddings> retrieveEmbeddings(String text) {
        var qembeddingsOptions = new EmbeddingsOptions(List.of(text));
        return openAIAsyncClient.getEmbeddings("text-embedding-3-small-1", qembeddingsOptions);
    }

    /**
     * Constructs a point structure from a list of float values representing a vector.
     *
     * @param point the vector values
     * @return a {@link Points.PointStruct} object containing the vector and associated metadata
     */
    private Points.PointStruct getPointStruct(List<Float> point, String originalText) {
        return Points.PointStruct.newBuilder().setId(id(UUID.randomUUID())).setVectors(vectors(point))
                .putAllPayload(Map.of("info", value(originalText))).build();
    }

    /**
     * Saves the list of point structures (vectors) to the Qdrant collection.
     *
     * @param pointStructs the list of vectors to be saved
     * @throws InterruptedException if the thread is interrupted during execution
     * @throws ExecutionException   if the saving operation fails
     */
    private void saveVector(ArrayList<Points.PointStruct> pointStructs)
            throws InterruptedException, ExecutionException {
        var updateResult = qdrantClient.upsertAsync(clientQdrantProperties.getCollectionName(), pointStructs).get();
        log.info(updateResult.getStatus().name());
    }


    public void processAndSaveTextList(List<EmbeddingRequest> request) {
        if (!request.isEmpty()) {
            request.forEach(r -> {
                try {
                    processAndSaveText(r.text());
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public Long getEmbeddingsQuantity() throws ExecutionException, InterruptedException {
        return qdrantClient.countAsync(clientQdrantProperties.getCollectionName()).get();
    }

    public QdrantResponse createCollection(String name) throws ExecutionException, InterruptedException {
        var defaultConnectionName = clientQdrantProperties.getCollectionName();
        if (name == null || name.isEmpty()) {
            name = defaultConnectionName;
        }
        var isCollectionExist = qdrantClient.collectionExistsAsync(name).get();
        if (!isCollectionExist) {
            var r1 = qdrantClient.createCollectionAsync(
                    Collections.CreateCollection.newBuilder().setCollectionName(name).setVectorsConfig(
                                    Collections.VectorsConfig.newBuilder().setParams(Collections.VectorParams.newBuilder()
                                            .setSize(clientQdrantProperties.getCollectionSize()).setDistance(
                                                    Collections.Distance.valueOf(
                                                            clientQdrantProperties.getCollectionDistance())).build()).build())
                            .build()).get();
            log.info("Collection {} created", name);
            return new QdrantResponse(r1.getTime(), "ok", r1.getResult(), "Collection '" + name + "' created");
        } else {
            log.info("Collection {} already exists", name);
            return new QdrantResponse(0, "ok", false, "Collection '" + name + "' already exists");
        }
    }

    public List<EmbeddingResponse> getEmbeddings(List<Points.ScoredPoint> scoredPoints, Integer limit) {
               return scoredPoints.stream()
                .map(scoredPoint -> new EmbeddingResponse(scoredPoint.getPayloadMap().get("info").getStringValue(),
                        scoredPoint.getScore())).limit(limit).toList();
    }
}

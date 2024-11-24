# Vector Databases

## 🧠 Continue Your Learning
🔗 [Semantic Kernel for Java Vector](https://devblogs.microsoft.com/semantic-kernel/announcing-semantic-kernel-for-java-1-2-0/#using-semantic-kernel-memory-(experimental)5)  
🔗 [DeepLearning AI: Understanding and Applying Text Embeddings](https://www.deeplearning.ai/short-courses/google-cloud-vertex-ai/)  
🔗 [DeepLearning AI: Vector Databases: from Embeddings to Applications](https://www.deeplearning.ai/short-courses/vector-databases-embeddings-applications/)  
🔗 [What is Payload in Qdrant](https://qdrant.tech/documentation/concepts/payload/)

Semantic Kernel works with vectorization without an HTTP wrapper, which is why we are sending requests ourselves using the OpenAI model.
Other frameworks also send requests to AI models, but they do so inside their own wrappers.
Additionally, Semantic Kernel lacks pre-made readers for different types of data sources, whereas other frameworks,
like Spring AI, provide specific tools for various data sources
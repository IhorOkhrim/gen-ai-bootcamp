# Vector Databases

## 📚 Learning Objectives
- Understand what is an embedding
- Understand how to store embeddings in a database.
- Learn to use vector databases for storing embeddings.
- Implement a simple application that builds and stores embeddings in a database.

## 📑 Task

### Use the tasks project from module 01
For this task and for each subsequent task, use the results from the previous task as a basis for new changes.

### Configure the Application
You need to configure the application to use a vector database for storing embeddings. You can use any vector database, such as `Postgres` with the `pgvector` plugin. 
Refer to the [Qdrant store autoconfiguration](https://github.com/qdrant/java-client?tab=readme-ov-file) for guidance. or you can use any other Vector DB, but we have example for it in _**examples**_ folder

### Implement REST API Functionality
Your task is to implement a REST API with the following functionality:

1. **Build Embedding from Text**
   - Implement an endpoint to build an embedding from the provided text.

2. **Build and Store Embedding from Text**
   - Implement an endpoint to build and store an embedding from the provided text.

3. **Search for Closest Embeddings**
   - Implement an endpoint to search for the closest embeddings in the database based on the input text.
   - Attach a screenshot showing the search for the closest embeddings in the database to your task on learn.epam.com.

### Closing
Create pull requests to your repository.
Attach link to your PR to your tasks on learn.epam.com.

### Evaluation Criteria
1. Configuration for Vector Database
- Correctly configured the application to use a vector database (e.g., Qdrant with qdrant plugin or you can use any other Vector DB) (15%)

2. Build Embedding from Text
- Implemented an endpoint to build an embedding from the provided text (15%)

3. Build and Store Embedding from Text
- Implemented an endpoint to build and store an embedding from the provided text (15%)

5. Search for Closest Embeddings
- Implemented an endpoint to search for the closest embeddings in the database based on the input text (25%)

6. Functionality Testing
- Application runs without errors (15%)
- Responses are generated correctly and are relevant to the prompts (15%)

## Self-Test Questions
You can index and test your knowledge by answering the following questions:
- What is embedding?
- How does input with embedding differ from input in a text format?
- Can a picture be converted to an embedding?
- What is Diversity measurement? What is Distance? What is Similarity in vectors?
- What is more important for text embedding: keeping a short distance for similar meaning, or keeping a short distance for semantically similar words?
- What is Clustering? What is Classification?
- What are vector DBs used for in GenAI?
- What is the dimensionality of the vector? What is the dimensionality of the model? What is the dimensionality of the vector DB?
- Can a vector built with one model be used with another model?
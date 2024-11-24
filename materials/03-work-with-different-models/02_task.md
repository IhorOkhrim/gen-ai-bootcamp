
# Working with Different Models

## 📚 Learning Objectives
- Meet with Azure AI Studio
- Review the compatibility of the Hugging Face
- Write an application that uses different models with Semantic Kernel

## 📑 Task

### Use the tasks project from module 01
For this task and for each subsequent task, use the results from the previous task as a basis for new changes.

### Call other models from Dial service:
For calling another model in Dial service deployment name should be changed. Example:

```yaml
client-azureopenai-deployment-name: Mixtral-8x7B-Instruct-v0.1
```

More deployment names can be retrieved from following API:

```shell
curl  -v 'https://ai-proxy.lab.epam.com/openai/deployments' \
-H "Api-Key: $MY_KEY"
```

Or by this [link](https://docs.epam-rail.com/supported-models)

Compare results for same prompts for different models and with using different PromptExecutionSettings. More details about PromptExecutionSettings are in  "Lab2" in the [`materials/lab2` folder](https://git.epam.com/epm-cdp/global-java-foundation-program/java-courses/-/tree/main/gen-ai-bootcamp/materials/02-promt-engineering)

### Closing
Create pull requests to your repository.
Attach link to your PR to your tasks on learn.epam.com.

### Free practice:
Modify the application to use the Semantic Kernel to generate images using Imagen model.

### Evaluation Criteria
1. Configuration for Different Models
- Correctly modified the application.properties file to change the deployment name for different models (15%)
- Successfully retrieved deployment names using the provided API (15%)

2. Implementation of Model Switching
- Correctly implemented the logic to switch between different models based on the deployment name (15%)
- Ensured that the application can call different models from the Dial service (15%)

3. Comparison of Results
- Compared results for the same prompts using different models (10%)
- Compared results using different PromptExecutionSettings (10%)
- Documented observations and differences in the results (10%)

4. Functionality Testing
- Application runs without errors (5%)
- Responses are generated correctly and are relevant to the prompts (5%)
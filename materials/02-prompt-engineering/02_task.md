# Prompt Engineering Fundamentals  

## 📚 Learning Objectives
 - Understand the importance of prompt engineering
 - Learn how to use prompts with Semantic Kernel
 - Learn how to parameterize the prompts
 - Learn how to use history

## 📑 Task 

### Use the tasks project from module 01
For this task and for each subsequent task, use the results from the previous task as a basis for new changes.

#### Use the version of Semantic Kernel starting from 1.2 or higher
Semantic Kernel as everything connected with AI rapidly evolves.
It is important to use the latest version of the library to get experience and understand of latest tools.

### Implement more complex code:
Initialize PromptExecutionSettings and fill it with some settings, temperature for example.

Try to play with the temperature value, what was changed?

Get ChatCompletionService and initiate ChatHistory for storing all user and system messages to ChatHistory.

Use ChatHistory to provide context about previous messages for a AI model.

Validate the result and print it to the console.

### Closing
Create pull requests to your repository.
Attach link to your PR to your tasks on learn.epam.com.

### Evaluation Criteria
1. Prompt Execution Settings Initialization
- Correctly initialized PromptExecutionSettings with the specified settings (20%)
- Demonstrated understanding of the impact of changing the temperature value (10%)

2. ChatCompletionService and ChatHistory Initialization
- Correctly initialized ChatCompletionService with the appropriate model and client (15%)
- Properly set up ChatHistory to store user and system messages (15%)

3. Function Setup for Chatting with OpenAI
- Correctly implemented the function to interact with OpenAI based on ChatHistory (20%)
- Validated the result and printed it to the console (10%)

4. Functionality Testing
- Application runs without errors (5%)
- Responses are generated correctly and are relevant to the prompts (5%)
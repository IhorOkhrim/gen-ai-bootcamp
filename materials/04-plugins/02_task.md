# Semantic Kernel Plugins

## 📚 Learning Objectives
- Learn different types of SK plugins
- Implement code that uses different types of SK plugins

## 📑 Task

### Use the tasks project from module 01
For this task and for each subsequent task, use the results from the previous task as a basis for new changes.

#### Use the version of Semantic Kernel starting from 1.2 or higher
Semantic Kernel as everything connected with AI rapidly evolves.
It is important to use the latest version of the library to get experience and understand of latest tools.

### Call function from custom plugin:
- Use [this](https://devblogs.microsoft.com/semantic-kernel/using-semantic-kernel-to-create-a-time-plugin-with-java/) is an example or you can follow [Microsoft documentation](https://learn.microsoft.com/en-us/semantic-kernel/concepts/plugins/?pivots=programming-language-java) 
- See the **SimplePlugin.java** usage in the **examples** folder of the course project

### Implement custom plugin
Your task is to create series of custom plugins the existing system.
This function should be called by a model based on user request, it can return some data or make actions inside the application.
As ideas for plugins you can use:
Age calculator, Weather forecast, Currency converter, Turn of the lamp (just change the flag of some boolean variable),
and so on, in general, any plugin that can do some calculations or provide some information outside model knowledge.

Note: no need to implement the real 3-rd party service integration, the response could be simply mocked.

### Closing
Create pull requests to your repository.
Attach link to your PR to your tasks on learn.epam.com.

### Evaluation Criteria
1. Configuration for Azure OpenAI Chat Completion Service
- Correctly modified the application.properties file to include the necessary settings for the Azure OpenAI chat completion service (10%)

2. Implementation and Integration several custom plugins
- Correctly implemented new function within the existing system (30%)
- System is capable of calling correct plugin based on user input (30%)
- System correctly generates and displays result using function based on user input (10%)

3. Functionality Testing
- Application runs without errors (10%)
- Responses are generated correctly and are relevant to the prompts (10%)

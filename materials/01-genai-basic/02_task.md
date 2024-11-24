# Introduction to Generative AI and Large Language Models

## 📚 Learning Objectives
- Understand the principles of Generative AI-based applications and the functioning of Large Language Models (LLMs).
- Explore Java frameworks supportive of AI and their applications.
- Develop a simple application using Semantic Kernel to perform text generation.

## 📑 Tasks

### High-level tasks
1. Set Up Project Environment (5%)
2. Obtain DIAL Key (2%)
3. Configuring the Application (13%)
4. Implement Application Logic (50%)
5. Final Integration and Submission (20%)
6. Free Practice: Explore and Innovate (10%)

### Detailed steps

#### Task 1: Set Up Project Environment
- Access and open the task code project located in the `tasks/gen_ai_training` folder provided in your course materials.
- This includes project configurations and dependencies essential for your work.

  **Weight: 5%**

#### Task 2: Obtain DIAL Key
- Utilize this link to secure your unique DIAL key which will be required for subsequent tasks.

  **Weight: 2%**

#### Task 3: Configuring the Application
- Edit the `application.properties` file to include the OpenAI chat service settings:

  ```yaml
  client-openai-key: {YOUR_KEY}
  client-openai-endpoint: https://your.ai-api.com/
  client-openai-deployment-name: gpt-xx-turbo
  ```

    - Properly inject OpenAI settings (**7%**).
    - Ensure all sensitive information remains placeholders, not hardcoded (**6%**).

  **Total Weight: 13%**

#### Task 4: Implement Application Logic
Develop a Controller:
- Create a controller that takes a user prompt via a request parameter and returns the chat-bot response in JSON format.

  ```json
  { "input": "I want to find top-10 books about world history" }
  ```

- Correct receipt of the prompt as a request parameter (**20%**).
- Accurate JSON formatted response containing the generated text (**20%**).

**Integrate Semantic Kernel 1.2+**:
- Ensure your application utilizes Semantic Kernel version 1.2 or above for generating responses.
    - Integration and application of the Semantic Kernel in the project (**5%**).
    - Effective usage of various prompts and settings leading to diverse responses (**5%**).

  **Total Weight: 50%**

#### Task 5: Final Integration and Submission
**Testing and Submission**:
- Ensure the application runs without errors and produces relevant responses (**15%**).
- Create a pull request for your branch verifying that the pipeline tests are successfully passing and your changes are in compliance (**5%**).

  **Total Weight: 20%**

### Free Practice: Explore and Innovate (**10%**)
**Experiment and Innovate**:
- Modify and test different settings using the Semantic Kernel chat completion services to see their impact on response generation and application functionality.
- Explore creative use of different prompts, and experiment with service settings to gauge variations in output.

**Documentation**:
- Document your findings and learnings from the experiments. Include examples of prompts and responses along with any quantifiable metrics or subjective assessments.

**Tips for Effective Experimentation**:
- Start Simple: Begin with clear, simple variations before moving to more complex alterations.
- Document Iteratively: Keep notes as you go rather than trying to remember all details at the end.
- Seek Patterns: Look for consistent trends or patterns that can inform best practices.
- Be Creative: Don't shy away from unusual or creative prompts and settings—it's about exploring possibilities!

  By following these guidelines and objectives, you can more effectively explore the capabilities and limitations of Generative AI technologies, enhancing your practical understanding and potentially innovative uses for these tools.
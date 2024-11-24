# Getting Started with this course
## Setup Java OpenJDK 21 or upper JDK 
OpenJDK 17 or newer
### Step 1: Check if OpenJDK 21 is Already Installed
1. Press the `Windows` key, type `cmd`, and press `Enter` to open the Command Prompt.
2. In the Command Prompt, type the following command and press `Enter`:3. Look for a version that starts with `17.` in the message. If you see such a version, it means the Java 17 SDK is already installed on your system, and you may not need to proceed with the installation. If it's not listed, follow the steps below to install the SDK.
### Step 2: Download the Java 21 SDK Installer
1. Open your preferred web browser.
2. Navigate to the Microsoft Build of OpenJDK download page at [https://www.oracle.com/java/technologies/javase-jdk21-downloads.html](https://learn.microsoft.com/en-us/java/openjdk/download#openjdk-21).
3. Locate the section for the Java 21 JDK.
4. Click on the "Download" button for the Windows version of the Java 21 JDK. Ensure you select the installer that matches your system architecture (x64, x86).
### Step 3: Run the Installer
1. Once the download is complete, navigate to the download location, typically the "Downloads" folder.
2. Double-click on the downloaded installer file (e.g., `microsoft-jdk-21.0.4-windows-x64.msi`) to launch the setup wizard.
3. If prompted by User Account Control (UAC), click "Yes" to allow the installer to make changes to your device.
4. Follow the on-screen instructions provided by the setup wizard to complete the installation. You can choose the default settings or customize the installation path according to your preferences.
5. Click "Close" to exit the setup wizard once the installation is finished.
### Step 4: Verify the Installation
1. To verify that the Java 21 SDK has been successfully installed, press the `Windows` key, type `cmd`, and press `Enter` to open the Command Prompt again.
2. In Command Prompt, type the following command and press `Enter`:
   ```
   java -version
   ```
3. This command should display the version of the Java SDK that was installed, confirming the success of the installation.


## Request OpenAI Key

## Clone this Repository

Cloning a repository allows you to freely experiment with changes locally without affecting the original project. 
Here is how you can clone the `gen-ai-bootcamp` repository from EPAM on GitHub.

### Navigate to the Repository and Review Guidelines

Git Repository Usage Guidelines [here](https://git.epam.com/epm-cdp/global-java-foundation-program/java-courses/-/blob/main/gen-ai-bootcamp/materials/00-course-setup/git.md)

### Code quality rules
Code quality rules for current training [here](https://git.epam.com/epm-cdp/global-java-foundation-program/java-courses/-/blob/main/gen-ai-bootcamp/materials/00-course-setup/Code_quality_rules_for_training.md)

### Documentation on LLM Frameworks:
Some documentation on LLM frameworks can be outdated. Please use the GitHub repository as the source of truth.  
Source code of Semantic Kernel is available [here](https://github.com/microsoft/semantic-kernel).  
Source code of Spring AI is available [here](https://github.com/spring-projects/spring-ai).  
Example of usage can be found in [samples in the same repository](https://github.com/microsoft/semantic-kernel/tree/main/prompt_template_samples).
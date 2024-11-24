# Git Repository Usage Guidelines

## Overview
This document provides step-by-step instructions for cloning the official repository, setting up your own public repository on GitHub, and maintaining proper workflow for contributions via pull requests.

## Initial Setup and Repository Cloning

### Clone the Official Repository
1. **Go to Official Repository URL** ([on original hosting platform](https://git.epam.com/epm-cdp/global-java-foundation-program/java-courses/-/tree/main/gen-ai-bootcamp/tasks/gen_ai_training)).
2. **Copy the Repository URL** from the repository interface.
3. **Clone the Repository Locally**. Run:
    ```bash
    git clone [official-repository-link]
    ```
   Replace `[official-repository-link]` with the copied URL.

### Set Up Your Public GitHub Repository

1. **Sign in to GitHub**:
    - Go to [GitHub](https://github.com/), and sign in or create an account if needed.
2. **Create a New Public Repository**:
    - Navigate to profile > Repositories tab > 'New'.
    - Name your repository, set it to **Public**, and skip initialization options. Click 'Create repository'.
3. **Link Your Local Repository to GitHub**:
    - Change directory to the cloned project:
    ```bash
    cd [your-local-repository-name]
    ```
    - Configure remote URLs:
    ```bash
    git remote rename origin upstream
    git remote add origin [your-github-repository-url]
    ```
   Replace `[your-github-repository-url]` with your new GitHub repository URL.
    - Push your code:
    ```bash
    git push -u origin main
    ```

## Workflow for Making Changes

### Use Branches for Features or Fixes
1. **Create a New Branch**:
    ```bash
    git checkout -b [new-branch-name]
    ```
   Replace `[new-branch-name]` with a descriptive branch name. **Include the module number** you are working on in the branch name (e.g., `module-1-feature`).
2. **Make Changes** on this new branch.
3. **Commit Your Changes**:
    ```bash
    git add .
    git commit -m "Detailed description of the changes"
    ```

### Push Changes & Create Pull Requests
1. **Push Your Branch to GitHub**:
    ```bash
    git push origin [new-branch-name]
    ```
2. **Create a Pull Request on GitHub**:
    - Navigate to 'Pull requests' tab > 'New pull request'.
    - Select your branch and the main branch for the merge.
    - Add title and description, create the pull request, and assign reviewers.

### Merge Pull Requests
- **Review and Approve**: Reviewers will examine your changes.
- **Merge the Pull Request**: Post-approval, use 'Squash and Merge' for a clean commit history.

## Best Practices
- **Use Clear Commit Messages**: Craft clear, concise commit messages for transparency and ease of tracking.

These guidelines ensure the requirement for using a public repository on GitHub, thus fostering transparency and collaboration. Following these protocols enhances the consistency and security of your project's code base while utilizing Git and GitHub's collaborative features effectively.
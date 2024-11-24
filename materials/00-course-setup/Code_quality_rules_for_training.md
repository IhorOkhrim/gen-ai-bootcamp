# Java Coding Standards for Training

This document outlines the coding standards to be adopted in the Java training modules. Adherence to these standards ensures that all code produced is consistent, readable, and maintainable.

## Table of Contents
1. [Naming Conventions](#naming-conventions)
2. [Formatting Rules](#formatting-rules)
    - [Indentation](#indentation)
    - [Line Length](#line-length)
    - [Whitespace](#whitespace)
    - [Braces](#braces)
3. [Commenting Practices](#commenting-practices)
    - [Inline Comments](#inline-comments)
    - [Javadoc](#javadoc)
4. [Programming Practices](#programming-practices)
    - [Error Handling](#error-handling)
    - [Use of Constants](#use-of-constants)
5. [File Structure](#file-structure)
6. [Code Review Guidelines](#code-review-guidelines)
7. [Tools and Environments](#tools-and-environments)

## Naming Conventions

### Classes and Interfaces
- Names should be nouns in PascalCase.
    - **Correct**: `Student`, `CustomerService`
    - **Incorrect**: `student`, `customer_service`

### Methods
- Names should be verbs in camelCase.
    - **Correct**: `saveOrder`, `deleteCustomer`
    - **Incorrect**: `Saveorder`, `delete_customer`

### Variables
- Use meaningful, descriptive names in camelCase.
    - **Correct**: `tableHeight`, `orderCount`
    - **Incorrect**: `th`, `ocount`

### Constants
- Constants should be in all uppercase with underscores separating words.
    - **Correct**: `MAX_SIZE`, `DEFAULT_VALUE`
    - **Incorrect**: `Maxsize`, `defaultvalue`

## Formatting Rules

### Indentation
Use 4 spaces for indentation. Do not use tabs.

### Line Length
Aim to keep lines no longer than 120 characters.

### Whitespace
Use blank lines to separate code blocks and single blank spaces around operators.

```java
int sum = a + b;
if (sum > threshold) {
    process(sum);
}
```

### Braces
Use K&R style ("Egyptian brackets") where the opening brace is on the same line as the statement and the closing one is lined up with the start of the statement.

```java
if (x == y) {
    doSomething();
}
```

## Commenting Practices

### Inline Comments
Use them sparingly to clarify complex parts of the code. Do not state the obvious.

### Javadoc
Use standard Javadoc comments for all public classes and methods.

```java
/**
 * Calculates and returns the sum of two integers.
 *
 * @param a the first integer
 * @param b the second integer
 * @return the sum of a and b
 */
public int calculateSum(int a, int b) {
    return a + b;
}
```

## Programming Practices

### Error Handling
Use exceptions rather than return codes. Handle specific exceptions if you know how to recover from them.

### Use of Constants
Replace magic numbers with named constants for clarity and ease of modification.

## File Structure
Keep each class in its own Java file. Files should be named after the class they contain.

## Code Review Guidelines
Use PR reviews to enforce standards.

## Where to find examples of code
You can find example of code in `gen-ai-bootcamp/examples`.

## Tools and Environments
Adopt tools like Checkstyle or SonarLint to enforce coding standards automatically. Configure IDEs to respect these formatting and style guidelines automatically.

Following these coding standards will lead to more reliable, maintainable, and consistent code throughout the training modules and beyond.g these coding standards will lead to more reliable, maintainable, and consistent code throughout the training modules and beyond.
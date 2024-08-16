
# Online Banking System - Software Testing Project

This repository contains the software testing project for an online banking system, developed as part of the **Software Testing & Validation** course (CSE-338) in 2023. The project includes unit tests and validation strategies to ensure the reliability, security, and quality of the banking application.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Testing Framework](#testing-framework)
- [Classes and Methods](#classes-and-methods)
- [Test Cases](#test-cases)
- [How to Run](#how-to-run)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The online banking system is a critical application that involves handling sensitive financial information and transactions. This project focuses on testing various components of the banking system, ensuring that they work as expected under different scenarios.

The test summary report included in this repository serves as a guide to the testing process, providing insights into the overall health of the application and the effectiveness of the testing cycle.

## Features

- **Unit Testing**: Covers various functionalities of the banking system such as user management, transactions, balance updates, and more.
- **Detailed Test Cases**: Includes a wide range of test cases for different scenarios, including edge cases, negative cases, and expected positive outcomes.
- **Test Report Generation**: Produces a comprehensive test summary report, which can be shared with stakeholders.

## Testing Framework

The testing framework used in this project includes:

- **JUnit**: For running unit tests and validating the behavior of individual methods.
- **Assert Statements**: Used extensively to verify that the output of each method matches the expected result.

## Classes and Methods

### `DBManagement` Class

This class handles database operations for the online banking system. Key methods include:

- `signUp()`: Registers a new user.
- `logIn()`: Validates user credentials.
- `updateBalanceWithAmount()`: Updates user balance.
- `transferMoney()`: Handles money transfers between users.
- `recordTransaction()`: Records a transaction in the system.
- **and more**...

### `DBManagement_Test` Class

This class includes test methods for validating the functionalities in the `DBManagement` class.

- `retrieveBalance_Test()`
- `updateBalanceWithAmount_Test()`
- `recordTransaction_Test()`
- **and more**...

### Additional Test Classes

- **`Deposit_Tests`**: Tests related to the deposit functionality.
- **`Login_Tests`**: Tests for login functionality, including cases for incorrect credentials.
- **`SignUp_Tests`**: Validates the user sign-up process.
- **`TransferMoney_Tests`**: Tests the money transfer functionality, including edge cases like insufficient balance.

## Test Cases

The project includes the following test cases:

- **Accepted Case Tests**: Validates the standard, expected functionality.
- **Negative Entry Tests**: Tests behavior with negative values or invalid input.
- **Boundary Tests**: Checks how the system behaves with boundary values like zero or maximum limits.
- **Duplicate Entry Tests**: Ensures the system correctly handles duplicate user details like username or national ID.


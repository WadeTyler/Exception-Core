# Exception Core

A Spring Boot microservice providing centralized exception handling and standardized error responses for distributed systems.

## Overview

Exception Core is a lightweight microservice that offers reusable exception handling components for Spring Boot applications. It provides a consistent error response format and handles common exception scenarios across your microservice architecture.

## Features

- **Global Exception Handling**: Centralized exception handling using `@RestControllerAdvice`
- **Standardized Error Responses**: Consistent JSON error format across all services
- **Custom HTTP Exceptions**: Flexible `HttpRequestException` with configurable HTTP status codes
- **Validation Support**: Built-in handling for Bean Validation errors
- **Ready-to-Use Components**: Interface-based design for easy integration

## Technology Stack

- **Java 24**
- **Spring Boot 3.5.3**
- **Spring Web**
- **Spring Validation**
- **Lombok**
- **Maven**
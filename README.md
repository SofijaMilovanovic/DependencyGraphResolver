# DependencyGraphResolver

A lightweight Java library for resolving and traversing package dependency graphs from a JSON file.

## Features

- Parse JSON files containing package dependencies.
- Build and traverse a dependency graph.
- Detect and handle cycles in the graph.
- Provide a "pretty" string representation of the resolved graph.

## Requirements

- **Java Version**: 23
- **Maven**: 3.8+ (or any build tool that supports Maven projects)

## Dependencies

- **Jackson Databind**: For JSON parsing (`com.fasterxml.jackson.core:jackson-databind:2.18.2`).
- **JUnit 5**: For testing (`org.junit.jupiter:junit-jupiter-api:5.11.4`).

## Installation

1. Clone the repository:
   ```bash
   git clone git@github.com:SofijaMilovanovic/DependencyGraphResolver.git
   or
   git clone https://github.com/SofijaMilovanovic/DependencyGraphResolver.git
   
   cd DependencyGraphResolver

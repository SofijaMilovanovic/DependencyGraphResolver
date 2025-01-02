# DependencyGraphResolver

A lightweight Java library for resolving and traversing package dependency graphs from a JSON file.

## Features

- Parse JSON files containing package dependencies.
- Build and traverse a dependency graph.
- Detect and handle cycles in the graph.
- Provide a "pretty" string representation of the resolved graph.

## Assumptions

While developing this library, the following assumptions were made:

Input Format: The library exclusively supports JSON files as input for loading and creating dependency graphs. Other
formats (e.g., XML, YAML) are not supported.
Dependency Type: The dependencies in the input JSON are expected to be strings. Other types (e.g., numbers, objects) are
not considered valid and will result in undefined behavior.
File Size: The input JSON files are assumed to be reasonably small, representing a group of packages and their
dependencies. For larger files, a streaming parser like Jackson Streaming API could be considered for better
performance.

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

## Licence

This project is private and not currently distributed under any license. If you have access to this repository, you are
authorized to use it for internal purposes.

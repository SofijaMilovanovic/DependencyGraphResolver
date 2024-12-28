package com.cern_application.dependencyresolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Utility class for loading dependency graphs from JSON files. */
public class DependencyLoader {

  /**
   * Loads a dependency graph from a JSON file.
   *
   * @param fileName the path to the JSON file containing the dependency graph
   * @return a populated DependencyGraph instance
   * @throws DependencyGraphException if an error occurs while loading the file or parsing the JSON
   */
  public static DependencyGraph loadFromFile(String fileName) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, List<String>> parsedGraph =
          mapper.readValue(new File(fileName), new TypeReference<Map<String, List<String>>>() {});

      DependencyGraph dependencyGraph = new DependencyGraph();
      for (Map.Entry<String, List<String>> entry : parsedGraph.entrySet()) {
        dependencyGraph.addDependencies(entry.getKey(), entry.getValue());
      }
      return dependencyGraph;
    } catch (IOException e) {
      throw new DependencyGraphException(
          "Failed to load or parse dependency graph from file: " + fileName, e);
    }
  }
}

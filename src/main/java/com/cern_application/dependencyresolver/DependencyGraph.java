package com.cern_application.dependencyresolver;

import java.util.*;

/**
 * The DependencyGraph class represents a directed dependency graph for packages.
 *
 * <p>This class supports adding dependencies for packages and retrieving the entire dependency
 * graph.
 *
 * <p>The graph is stored as a mapping where each key represents a package name and the associated
 * value is a list of dependencies (other package names).
 *
 * <p>Note: Package names and dependencies are assumed to be strings, based on the input JSON
 * format. If requirements change (e.g., using unique IDs or objects), the implementation should be
 * updated.
 */
public class DependencyGraph {

  private final Map<String, List<String>> graph;

  public DependencyGraph() {
    this.graph = new HashMap<>();
  }

  /**
   * Adds or replaces dependencies for a specific package.
   *
   * <p>If the package already exists in the graph, its dependencies will be replaced with the new
   * list provided.
   *
   * @param node the name of the package (must not be null)
   * @param dependencies the list of dependencies for the package (must not be null)
   * @throws DependencyGraphException if either the node or dependencies are null
   */
  public void addDependencies(String node, List<String> dependencies) {
    if (node == null || dependencies == null) {
      throw new DependencyGraphException("Node and dependencies cannot be null.");
    }
    graph.put(node, dependencies);

    if (hasCycle()) {
      graph.remove(node);
      throw new DependencyGraphException(
          "Adding dependencies for node '" + node + "' introduces a circular dependency.");
    }
  }

  /**
   * Retrieves the entire dependency graph as an unmodifiable map.
   *
   * <p>The graph is returned as a mapping where:
   *
   * <ul>
   *   <li>Keys represent package names
   *   <li>Values represent lists of dependencies for the corresponding packages
   * </ul>
   *
   * @return an unmodifiable view of the dependency graph
   */
  public Map<String, List<String>> getGraph() {
    return Collections.unmodifiableMap(graph);
  }

  /**
   * Checks if the graph contains any circular dependencies.
   *
   * @return true if a circular dependency exists, false otherwise
   */
  private boolean hasCycle() {
    Set<String> visited = new HashSet<>();
    Set<String> stack = new HashSet<>();

    for (String node : graph.keySet()) {
      if (detectCycle(node, visited, stack)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Recursively detects a cycle starting from the given node.
   *
   * @param node the current node being checked
   * @param visited tracks all nodes that have been fully processed
   * @param stack tracks nodes currently in the recursion stack
   * @return true if a cycle is detected, false otherwise
   */
  private boolean detectCycle(String node, Set<String> visited, Set<String> stack) {
    if (stack.contains(node)) {
      return true;
    }
    if (visited.contains(node)) {
      return false;
    }
    visited.add(node);
    stack.add(node);

    for (String dependency : graph.getOrDefault(node, Collections.emptyList())) {
      if (detectCycle(dependency, visited, stack)) {
        return true;
      }
    }

    stack.remove(node);
    return false;
  }
}

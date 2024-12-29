package com.cern_application.dependencyresolver;

import java.util.*;

/**
 * Resolves and provides representations of a dependency graph.
 *
 * <p>This class is responsible for resolving dependencies in a directed graph and generating a
 * hierarchical representation of the graph. The graph is provided as a {@link DependencyGraph},
 * which maps package names to their respective dependencies.
 *
 * <p>Main functionalities include:
 *
 * <ul>
 *   <li>Resolving the full dependency graph, including duplicate entries for each occurrence
 *   <li>Generating a pretty, indented string representation of the resolved graph
 * </ul>
 *
 * <p>The {@code DependencyResolver} ensures traversal of all dependencies and avoids infinite loops
 * by tracking visited nodes during resolution.
 */
public class DependencyResolver {

  private final DependencyGraph graph;

  /**
   * Constructs a DependencyResolver with the specified DependencyGraph.
   *
   * @param graph The DependencyGraph to resolve. Must not be null.
   * @throws DependencyGraphException if the provided graph is null.
   */
  public DependencyResolver(DependencyGraph graph) {
    if (graph == null) {
      throw new DependencyGraphException("DependencyGraph cannot be null.");
    }
    this.graph = graph;
  }

  /**
   * Resolves the dependency graph fully, including duplicates for every reference in the structure.
   *
   * @return A list representing the fully resolved graph with duplicates.
   */
  public List<String> resolveFullGraph() {
    List<String> fullResolved = new ArrayList<>();
    for (String node : graph.getGraph().keySet()) {
      resolveNodeWithDuplicates(node, fullResolved);
    }
    return fullResolved;
  }

  /**
   * Recursively resolves a node's dependencies and appends them in traversal order, including
   * duplicates for every occurrence.
   *
   * @param node The current node to resolve.
   * @param fullResolved The list to store the resolved dependencies.
   */
  private void resolveNodeWithDuplicates(String node, List<String> fullResolved) {
    fullResolved.add(node);

    List<String> dependencies = graph.getGraph().getOrDefault(node, Collections.emptyList());
    for (String dependency : dependencies) {
      resolveNodeWithDuplicates(dependency, fullResolved);
    }
  }

  /**
   * Provides a pretty string representation of the fully resolved dependency graph.
   *
   * <p>The representation displays each node and its dependencies hierarchically, with proper
   * indentation to indicate depth levels.
   *
   * @return A formatted string representation of the resolved graph.
   */
  public String getPrettyGraph() {
    StringBuilder builder = new StringBuilder();
    Set<String> visited = new HashSet<>();
    for (String node : graph.getGraph().keySet()) {
      buildPrettyGraph(node, builder, 0, visited);
    }
    return builder.toString();
  }

  /**
   * Recursively builds the pretty graph with appropriate indentation.
   *
   * <p>The method traverses the graph recursively, adding each node and its dependencies to the
   * output with indentation proportional to their depth in the hierarchy.
   *
   * @param node The current node being processed.
   * @param builder The StringBuilder to construct the output.
   * @param level The current indentation level.
   * @param visited Tracks visited nodes to avoid infinite loops.
   */
  private void buildPrettyGraph(
      String node, StringBuilder builder, int level, Set<String> visited) {
    if (visited.contains(node)) {
      return;
    }
    visited.add(node);

    builder.append("  ".repeat(level)).append("- ").append(node).append("\n");

    List<String> dependencies = graph.getGraph().getOrDefault(node, Collections.emptyList());
    for (String dependency : dependencies) {
      buildPrettyGraph(dependency, builder, level + 1, visited);
    }
    visited.remove(node);
  }
}

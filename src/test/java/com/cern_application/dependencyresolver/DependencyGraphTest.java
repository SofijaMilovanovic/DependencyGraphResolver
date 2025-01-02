package com.cern_application.dependencyresolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class DependencyGraphTest {
  @Test
  void testAddDependenciesAndRetrieveGraph() {
    DependencyGraph graph = new DependencyGraph();
    graph.addDependencies("pkg1", List.of("pkg2", "pkg3"));
    graph.addDependencies("pkg2", List.of("pkg3"));
    graph.addDependencies("pkg3", List.of());

    Map<String, List<String>> result = graph.getGraph();

    assertEquals(3, result.size());
    assertEquals(List.of("pkg2", "pkg3"), result.get("pkg1"));
    assertEquals(List.of("pkg3"), result.get("pkg2"));
    assertEquals(List.of(), result.get("pkg3"));
  }

  @Test
  void testEmptyGraph() {
    DependencyGraph graph = new DependencyGraph();

    Map<String, List<String>> result = graph.getGraph();
    assertTrue(result.isEmpty());
  }

  @Test
  void testCircularDependencyThrowsException() {
    DependencyGraph graph = new DependencyGraph();
    graph.addDependencies("pkg1", List.of("pkg2", "pkg3"));
    graph.addDependencies("pkg2", List.of("pkg3"));

    Executable circularDependency = () -> graph.addDependencies("pkg3", List.of("pkg1"));
    DependencyGraphException exception =
        assertThrows(DependencyGraphException.class, circularDependency);

    assertTrue(exception.getMessage().contains("circular dependency"));
  }

  @Test
  void testAddDependenciesWithInvalidInput() {
    DependencyGraph graph = new DependencyGraph();

    Executable nullNode = () -> graph.addDependencies(null, List.of("pkg2"));
    Executable nullDependencies = () -> graph.addDependencies("pkg1", null);

    assertThrows(DependencyGraphException.class, nullNode);
    assertThrows(DependencyGraphException.class, nullDependencies);
  }
}

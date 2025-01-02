package com.cern_application.dependencyresolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class DependencyLoaderTest {

  @Test
  void testLoadEmptyGraph() {
    String filePath = getClass().getClassLoader().getResource("emptyGraph.json").getFile();
    DependencyGraph graph = DependencyLoader.loadFromFile(filePath);

    assertNotNull(graph);
    assertTrue(graph.getGraph().isEmpty());
  }

  @Test
  void testLoadValidGraph() {
    String filePath = getClass().getClassLoader().getResource("validGraph.json").getFile();
    DependencyGraph graph = DependencyLoader.loadFromFile(filePath);

    assertNotNull(graph);
    assertEquals(3, graph.getGraph().size());
    assertEquals(List.of("pkg2", "pkg3"), graph.getGraph().get("pkg1"));
    assertEquals(List.of("pkg3"), graph.getGraph().get("pkg2"));
    assertEquals(List.of(), graph.getGraph().get("pkg3"));
  }

  @Test
  void testLoadGraphWithCircularDependency() {
    String filePath =
        getClass().getClassLoader().getResource("circularDependencyGraph.json").getFile();

    DependencyGraphException exception =
        assertThrows(DependencyGraphException.class, () -> DependencyLoader.loadFromFile(filePath));

    assertTrue(exception.getMessage().contains("circular dependency"));
  }
}

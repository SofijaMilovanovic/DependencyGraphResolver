package com.cern_application.dependencyresolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DependencyResolverTest {
    @Test
    public void testResolveFullGraph() {
        DependencyGraph graph = new DependencyGraph();
        graph.addDependencies("pkg1", List.of("pkg2", "pkg3"));
        graph.addDependencies("pkg2", List.of("pkg3"));
        graph.addDependencies("pkg3", List.of());

        DependencyResolver resolver = new DependencyResolver(graph);

        List<String> result = resolver.resolveFullGraph();
        assertEquals(List.of("pkg1", "pkg2", "pkg3", "pkg3", "pkg2", "pkg3", "pkg3"), result);
    }

    @Test
    public void testPrettyGraph() {
        DependencyGraph graph = new DependencyGraph();
        graph.addDependencies("pkg1", List.of("pkg2", "pkg3"));
        graph.addDependencies("pkg2", List.of("pkg3"));
        graph.addDependencies("pkg3", List.of());

        DependencyResolver resolver = new DependencyResolver(graph);

        String prettyGraph = resolver.getPrettyGraph();
        String expected =
                "- pkg1\n" +
                        "  - pkg2\n" +
                        "    - pkg3\n" +
                        "  - pkg3\n" +
                        "- pkg2\n" +
                        "  - pkg3\n" +
                        "- pkg3\n";

        assertEquals(expected, prettyGraph);
    }
}

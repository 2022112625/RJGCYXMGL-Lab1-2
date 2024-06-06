/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends P1.graph.GraphInstanceTest {

    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    @Test
    public void testConcreteVerticesGraphToString() {
        Graph<String> graph = emptyInstance();

        assertEquals("Vertices: ", graph.toString());

        graph.add("A");
        assertEquals("Vertices: A", graph.toString());

        graph.add("B");
        graph.add("C");
        assertEquals("Vertices: A, B, C", graph.toString());
    }

    @Test
    public void testVertexOperations() {
        Vertex vertex = new Vertex("A");

        assertEquals("A", vertex.getLabel());

        assertTrue(vertex.equals(new Vertex("A")));
        assertFalse(vertex.equals(new Vertex("B")));

        assertEquals("A".hashCode(), vertex.hashCode());

        assertEquals("A", vertex.toString());
    }


}

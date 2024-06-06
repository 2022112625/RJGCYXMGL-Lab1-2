package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 *
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 *
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends P1.graph.GraphInstanceTest {

    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    @Test
    public void testConcreteEdgesGraphToString() {
        Graph<String> graph = emptyInstance();
        assertEquals("{}", graph.toString());

        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals("{A=[(A -> B: 5)], B=[]}", graph.toString());
    }
    @Test
    public void testEdgeOperations() {
        Edge edge = new Edge("A", "B", 5);
        assertEquals("A", edge.source);
        assertEquals("B", edge.target);
        assertEquals(5, edge.weight);

        assertEquals("(A -> B: 5)", edge.toString());
    }

}
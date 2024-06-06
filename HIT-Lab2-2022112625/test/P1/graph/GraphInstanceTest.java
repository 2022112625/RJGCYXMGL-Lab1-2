package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 *
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    //   TODO

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();

        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));

        assertFalse(graph.add("A"));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();

        graph.add("A");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));

        assertFalse(graph.remove("B"));
    }

    @Test
    public void testAddEdge() {
        Graph<String> graph = emptyInstance();

        graph.add("A");
        graph.add("B");

        assertEquals(0, graph.set("A", "B", 5));
        assertTrue(graph.targets("A").containsKey("B"));

        assertEquals(5, graph.set("A", "B", 10));
        assertEquals(10, (int) graph.targets("A").get("B"));
    }

    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();

        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);

        assertEquals(5, graph.set("A", "B", 0));
        assertFalse(graph.targets("A").containsKey("B"));

        assertEquals(0, graph.set("A", "B", 0));
        assertFalse(graph.targets("A").containsKey("B"));
    }


}


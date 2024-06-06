package P1.graph;

import java.util.*;

/**
 * An implementation of Graph without using Edge objects.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final Map<String, Map<String, Integer>> edges = new HashMap<>();
    public ConcreteVerticesGraph() {
    }

    // checkRep
    private void checkRep() {
        Set<String> uniqueVertices = new HashSet<>(vertices);
        assert uniqueVertices.size() == vertices.size() : "Duplicate vertices found!";
    }

    @Override
    public boolean add(String vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        edges.put(vertex, new HashMap<>());
        checkRep();
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        if (!vertices.contains(source) || !vertices.contains(target)) {
            throw new IllegalArgumentException("Source or target vertex does not exist");
        }

        Map<String, Integer> sourceEdges = edges.get(source);
        int previousWeight = sourceEdges.getOrDefault(target, 0);
        if (weight == 0) {
            sourceEdges.remove(target);
        } else {
            sourceEdges.put(target, weight);
        }
        return previousWeight;
    }

    @Override
    public boolean remove(String vertex) {
        boolean removed = vertices.remove(vertex);
        edges.remove(vertex);
        edges.values().forEach(map -> map.remove(vertex));
        checkRep();
        return removed;
    }

    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Map<String, Integer> sources(String target) {
        if (!vertices.contains(target)) {
            throw new IllegalArgumentException("Target vertex does not exist");
        }
        Map<String, Integer> sourceMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : edges.entrySet()) {
            String source = entry.getKey();
            Map<String, Integer> targets = entry.getValue();
            if (targets.containsKey(target)) {
                sourceMap.put(source, targets.get(target));
            }
        }
        return sourceMap;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        if (!vertices.contains(source)) {
            throw new IllegalArgumentException("Source vertex does not exist");
        }
        return edges.getOrDefault(source, Collections.emptyMap());
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ");
        Iterator<String> iterator = vertices.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }


}



/**
 * Represents a vertex in the graph.
 */
class Vertex {

    private final String label;

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) obj;
        return label.equals(vertex.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label;
    }
}

package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final Map<String, Map<String, Integer>> edges = new HashMap<>();

    @Override
    public boolean add(String vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        edges.put(vertex, new HashMap<>());
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
        if (!vertices.contains(vertex)) {
            return false;
        }
        vertices.remove(vertex);
        edges.remove(vertex);
        edges.values().forEach(map -> map.remove(vertex));
        return true;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (String vertex : vertices) {
            sb.append(vertex).append("=[");
            Map<String, Integer> targetMap = edges.getOrDefault(vertex, Collections.emptyMap());
            for (Map.Entry<String, Integer> entry : targetMap.entrySet()) {
                sb.append("(").append(vertex).append(" -> ").append(entry.getKey()).append(": ").append(entry.getValue()).append("), ");
            }
            if (!targetMap.isEmpty()) {
                sb.delete(sb.length() - 2, sb.length());
            }
            sb.append("], ");
        }

        if (!vertices.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("}");
        return sb.toString();
    }
}

class Edge {
    String source; // 源顶点
    String target; // 目标顶点
    int weight;    // 权重

    // 构造方法
    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    // 重写 toString() 方法，用于调试和输出
    @Override
    public String toString() {
        return "(" + source + " -> " + target + ": " + weight + ")";
    }

}
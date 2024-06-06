package P2;
import P1.graph.ConcreteEdgesGraph;

import java.util.*;

/**
 * 表示一个社交网络图，其中每个人都连接到零个或多个人。
 */
public class FriendshipGraph {
    private final Map<Person, Integer> distances;
    private final ConcreteEdgesGraph graph;

    /**
     * 构造一个 FriendshipGraph。
     */
    public FriendshipGraph() {
        this.distances = new HashMap<>();
        this.graph = new ConcreteEdgesGraph();
    }

    /**
     * 添加一个人到图中。
     *
     * @param person 要添加的人
     */
    public void addVertex(Person person) {
        if (!graph.vertices().contains(person.getName())) {
            graph.add(person.getName());
        }
    }

    /**
     * 添加两个人之间的友谊关系。
     *
     * @param person1 第一个人
     * @param person2 第二个人
     */
    public void addEdge(Person person1, Person person2) {
        graph.set(person1.getName(), person2.getName(), 1);
        graph.set(person2.getName(), person1.getName(), 1);
    }

    /**
     * 计算两个人之间的最短距离。
     *
     * @param person1 第一个人
     * @param person2 第二个人
     * @return 两个人之间的最短距离，如果它们不相连，则返回-1
     */
    public int getDistance(Person person1, Person person2) {
        if (!graph.vertices().contains(person1.getName()) || !graph.vertices().contains(person2.getName())) {
            return -1; // 一个或两个人不在图中
        }

        Map<String, Integer> distances = computeDistances(person1);

        return distances.getOrDefault(person2.getName(), -1);
    }

    /**
     * 计算从源人到所有其他可达人的距离。
     *
     * @param source 源人
     * @return 从源人到所有可达人的距离的映射
     */
    private Map<String, Integer> computeDistances(Person source) {
        Map<String, Integer> distances = new HashMap<>();
        for (String person : graph.vertices()) {
            distances.put(person, -1); // 初始化距离为-1（无穷大）
        }

        distances.put(source.getName(), 0); // 源人到自己的距离为0

        // 执行 BFS 来计算距离
        // 这是使用修改过的 BFS 版本执行的，它还跟踪距离
        Queue<String> queue = new LinkedList<>();
        queue.offer(source.getName());

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = distances.get(current);

            Map<String, Integer> neighbors = graph.targets(current);
            for (String neighbor : neighbors.keySet()) {
                if (distances.get(neighbor) == -1) { // 如果距离尚未计算
                    distances.put(neighbor, currentDistance + 1);
                    queue.offer(neighbor);
                }
            }
        }

        return distances;
    }
}

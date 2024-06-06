package P1.poet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 */
public class GraphPoet {

    private final Graph<String> graph = new ConcreteVerticesGraph();

    // 构造函数
    public GraphPoet(File corpus) throws IOException {
        // 从语料库构建词语亲和图
        List<String> words = extractWords(corpus);
        buildGraph(words);
    }

    // 从语料库文件中提取单词
    private List<String> extractWords(File file) throws IOException {
        List<String> words = new ArrayList<>();
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            scanner.useDelimiter("\\s+"); // 使用空格作为分隔符
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        }
        return words;
    }


    public String poem(String input) {
        String[] words = input.split("\\s+");
        StringBuilder poemBuilder = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            String prevWord = words[i];
            String nextWord = words[i + 1];
            if (!graph.vertices().contains(prevWord)) {
                poemBuilder.append(prevWord).append(" ");
                continue;
            }
            poemBuilder.append(prevWord).append(" ");
            String bridgeWord = findBridgeWord(prevWord, nextWord);
            if (bridgeWord != null) {
                poemBuilder.append(bridgeWord.toLowerCase()).append(" ");
            } else {
                poemBuilder.append(nextWord).append(" ");
            }
        }
        if (words.length > 0 && !graph.vertices().contains(words[words.length - 1])) {
            poemBuilder.append(words[words.length - 1]);
        }
        return poemBuilder.toString();
    }

    // 构建词语亲和图
    private void buildGraph(List<String> words) {
        String prevWord = null;
        for (String word : words) {
            if (prevWord != null) {
                // 添加边前检查单词是否存在于图中
                if (!graph.vertices().contains(prevWord)) {
                    graph.add(prevWord);
                }
                if (!graph.vertices().contains(word)) {
                    graph.add(word);
                }
                // 检查源顶点是否存在
                if (!graph.sources(word).isEmpty()) {
                    // 添加边
                    graph.set(prevWord, word, graph.set(prevWord, word, 0) + 1);
                }
            }
            prevWord = word;
        }
    }

    //寻找桥接词
    private String findBridgeWord(String prevWord, String nextWord) {
        Map<String, Integer> targets = graph.targets(prevWord);
        String bridgeWord = null;
        int maxWeight = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : targets.entrySet()) {
            String target = entry.getKey();
            int weight = entry.getValue();
            if (graph.targets(target).containsKey(nextWord) && weight > maxWeight) {
                bridgeWord = target;
                maxWeight = weight;
            }
        }
        return bridgeWord;
    }

}
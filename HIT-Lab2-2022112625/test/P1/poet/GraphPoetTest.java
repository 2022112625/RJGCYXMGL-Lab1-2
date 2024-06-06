package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    // 测试构造函数是否能够正确构建词语亲和图
    @Test
    public void testGraphPoetConstructor() throws IOException {
        File corpus = new File("src/P1/poet/mugar-omni-theater.txt");
        GraphPoet poet = new GraphPoet(corpus);
        assertNotNull(poet);
    }

    // 测试生成诗歌功能是否正常
    @Test
    public void testPoemGeneration() throws IOException {
        File corpus = new File("src/P1/poet/mugar-omni-theater.txt");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Test the system.";
        String expectedOutput = "Test the system. ";
        assertEquals(expectedOutput, poet.poem(input));
    }
}
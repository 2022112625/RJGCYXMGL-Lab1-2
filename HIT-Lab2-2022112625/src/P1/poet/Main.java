package P1.poet;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("src/poet/mugar-omni-theater.txt"));
        final String input = "Test the system.";
        System.out.println(input + "\n>>>\n" + nimoy.poem(input));
    }

}
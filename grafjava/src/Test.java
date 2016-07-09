import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Grzecho on 08.07.2016.
 */
public class Test {

    public static void main(String[] args) {
        Graf graf = readFile("graph.txt");
        NStarAlgorytm a = new NStarAlgorytm(graf);
        a.execute(graf.getWierzcholki().get(1), graf.getWierzcholki().get(6));
    }


    private static Graf readFile(String fileName) {
        List<Vertex> vertexList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL resource = classloader.getResource(fileName);

        try (Stream<String> stream = Files.lines(Paths.get(resource.toURI()))) {

            stream.forEach(line -> process(line, vertexList, edgeList));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Graf graf = new Graf(vertexList, edgeList);
        return graf;
    }

    private static void process(String line, List<Vertex> vertexList, List<Edge> edgeList) {
        if (!line.contains("//")) {
            if (line.trim().contains("V("))
                generateVertex(line, vertexList);
            if (line.trim().contains("E("))
                generateEdge(line, edgeList, vertexList);
        }

    }

    private static String subString(String s) {
        s = s.replaceAll("\\s+", "");
        return s.substring(s.indexOf("(") + 1, s.indexOf(")"));
    }

    private static void generateVertex(String s, List<Vertex> vertexList) {
        s = subString(s);
        String[] vertexFromFile = s.split(",");
        vertexList.add(new Vertex(vertexFromFile[0], Integer.valueOf(vertexFromFile[1])));
    }

    private static void generateEdge(String s, List<Edge> edgeList, List<Vertex> vertexList) {
        s = subString(s);
        String[] edgeFromFile = s.trim().split(",");
        edgeList.add(new Edge(findVertexByName(edgeFromFile[0], vertexList), findVertexByName(edgeFromFile[1], vertexList), Integer.valueOf(edgeFromFile[2])));
    }


    private static Vertex findVertexByName(String name, List<Vertex> vertexList) {
        Vertex vertex = vertexList.stream().
                filter(v -> (v.getNameId().equals(name)))
                .findFirst().get();

        return vertex;
    }


}

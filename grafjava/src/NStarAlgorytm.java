import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Grzecho on 08.07.2016.
 */
public class NStarAlgorytm {

    private final Graf graf;
    private Set<Vertex> closedset;
    private Set<Vertex> openset;
    private Map<Vertex, Integer> gScore;
    private Map<Vertex, Integer> fScore;
    private Map<Vertex, Vertex> cameFrom;

    public NStarAlgorytm(Graf graf) {
        this.graf = graf;
        this.openset = new HashSet<>();
        this.closedset = new HashSet<>();
        this.gScore = new HashMap<>();
        this.fScore = new HashMap<>();
        this.cameFrom = new HashMap<>();
    }

    public void execute(Vertex source, Vertex destination) {
        openset.add(source);
        gScore.put(source, 0);
        fScore.put(source, source.getH());
        while (!openset.isEmpty()) {
            Vertex current = findVertexWithLowestHeuristic();
            if (current.equals(destination))
                reconstruct_path(destination);

            openset.remove(current);
            closedset.add(current);

            Set<Vertex> neighbors = getNeighbors(current);
            for (Vertex w : neighbors) {
                if (closedset.contains(w))
                    continue;

                int tentative_gScore = gScore.getOrDefault(current, 0) + getDistanceBetween(current, w);
                boolean gScoreIsBest = false;

                if (!openset.contains(w)) {
                    openset.add(w);
                    gScoreIsBest = true;
                } else if (tentative_gScore < gScore.getOrDefault(w, 0)) {
                    gScoreIsBest = true;
                }

                if (gScoreIsBest) {
                    cameFrom.put(w, current);
                    gScore.put(w, tentative_gScore);
                    fScore.put(w, gScore.getOrDefault(w, 0) + w.getH());
                }

            }
        }
    }

    private Set<Vertex> getNeighbors(Vertex v) {
        Set<Vertex> neighbors = new HashSet<>();
        for (Edge k : graf.getKrawedzie()) {
            if (k.getPointX().equals(v) && !isInClosedSet(k.getPointY())) {
                neighbors.add(k.getPointY());
            }
            if (k.getPointY().equals(v) && !isInClosedSet(k.getPointX())) {
                neighbors.add(k.getPointX());
            }
        }
        return neighbors;
    }

    private boolean isInClosedSet(Vertex v) {
        return closedset.contains(v);
    }

    private Vertex findVertexWithLowestHeuristic() {

        Map<Vertex, Integer> collect = fScore.entrySet().stream()
                .filter(map -> openset.contains(map.getKey()))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        Vertex key2 = collect.entrySet().stream().min((entry1, entry2) -> Integer.compare(entry1.getValue(), entry2.getValue())).get().getKey();

        return key2;
    }

    private int getDistanceBetween(Vertex source, Vertex destination) {
        Edge edge = graf.getKrawedzie().stream().
                filter(k -> (k.getPointX().equals(source) && k.getPointY().equals(destination)) ||
                        (k.getPointX().equals(destination) && k.getPointY().equals(source)))
                .findFirst().get();
        return edge.getWeight();
    }

    private String reconstruct_path(Vertex current) {
        StringBuilder sb = new StringBuilder();
        sb.append("The Shortest path: " + current.getNameId() + " ");
        while (this.cameFrom.containsKey(current)) {
            current = this.cameFrom.get(current);
            sb.append(current.getNameId() + " ");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


}

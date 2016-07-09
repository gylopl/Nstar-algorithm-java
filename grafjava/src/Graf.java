import java.util.List;

/**
 * Created by Grzecho on 08.07.2016.
 */
public class Graf {
    private final List<Vertex> wierzcholki;
    private final List<Edge> krawedzie;

    public Graf(List<Vertex> wierzcholki, List<Edge> krawedzie) {
        this.wierzcholki = wierzcholki;
        this.krawedzie = krawedzie;
    }

    public List<Vertex> getWierzcholki() {
        return wierzcholki;
    }

    public List<Edge> getKrawedzie() {
        return krawedzie;
    }
}

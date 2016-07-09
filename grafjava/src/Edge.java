/**
 * Created by Grzecho on 08.07.2016.
 */
public class Edge {

    private final Vertex pointX;
    private final Vertex pointY;
    private final int weight;

    public Edge(Vertex pointX, Vertex pointY, int weight) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.weight = weight;
    }


    public Vertex getPointX() {
        return pointX;
    }

    public Vertex getPointY() {
        return pointY;
    }

    public int getWeight() {
        return weight;
    }
}

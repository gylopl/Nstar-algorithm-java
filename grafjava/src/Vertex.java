/**
 * Created by Grzecho on 08.07.2016.
 */
public class Vertex {

    final private String nameId;
    final private int h;

    public Vertex(String nameId, int h) {
        this.nameId = nameId;
        this.h = h;
    }

    public int getH() {
        return h;
    }

    public String getNameId() {
        return nameId;
    }
}

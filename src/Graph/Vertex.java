package Graph;

import java.util.*;

public class Vertex {
    public int index;
    private int key;
    public Vertex parent;

    public Vertex() {
        this.index = 0;
        this.key = 0;
        this.parent = null;
    }

    public Vertex(int index) {
        this.index = index;
    }

    public Vertex(int index, int key, Vertex parent) {
        this.index = index;
        this.key = key;
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
//    public Vertex[] adjacentVertices;
//    private weight


    public static class VertexComparator implements Comparator<Vertex> {
    // Overriding compare()method of Comparator
    // for ascending order of key
    public int compare(Vertex v1, Vertex v2) {
        if (v1.key > v2.key)
            return 1;
        else if (v1.key < v2.key)
            return -1;
        return 0;
    }
}


}

package Graph;

public class Edge {
    public int index;
    public int weight;
    public int parentIndex;

    public Edge(int index) {
        this.index = index;
        this.parentIndex = index;
    }

    public Edge(int parentIndex, int index, int weight) {
        this.index = index;
        this.weight = weight;
        this.parentIndex = parentIndex;
    }

}
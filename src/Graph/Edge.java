package Graph;

public class Edge {
    int index;
    int weight;
    int parentIndex;

    public Edge(int index) {
        this.index = index;
        this.parentIndex = index;
    }

    public Edge(int index, int weight, int parentIndex) {
        this.index = index;
        this.weight = weight;
        this.parentIndex = parentIndex;
    }

}
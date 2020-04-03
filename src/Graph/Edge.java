package Graph;

public class Edge {
//    public Vertex vertex2;
//    public Vertex vertex1;
    public int vertex1;
    public int vertex2;
    public int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }
}

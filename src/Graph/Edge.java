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

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (index == other.index && parentIndex == other.parentIndex) {
            return true;
        } else if ( index == other.parentIndex && parentIndex == other.index )
            return true;
        return false;
    }
}
package Graph;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.LinkedList;

public class AdjacencyList {
    public LinkedList<Edge>[] adjacencyList; // Array of Linked Lists
    public ArrayList<Vertex> vertices;
    public int numberOfVertices;

    // Constructor
    public AdjacencyList(int numberOfVertices) {
        adjacencyList = new LinkedList[numberOfVertices];
        vertices = new ArrayList<>();
        for ( int i = 0; i < numberOfVertices; i++){
            adjacencyList[i] = new LinkedList<Edge>();
            vertices.add(new Vertex(i));
        }
        this.numberOfVertices = numberOfVertices;
    }
    public void add(int parentIndex, int index, int weight ){
        adjacencyList[parentIndex].add(new Edge(parentIndex,index,weight));
        adjacencyList[index].add(new Edge(index,parentIndex,weight));
    }

    public void print(){
//        Edge tempNode;
        for ( int i = 0; i < numberOfVertices; i++ ){
            System.out.print("Vertex " + i);
            adjacencyList[i].forEach((temp) -> {
                        System.out.print("-> " + temp.index); });
            System.out.println();
        }
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

}

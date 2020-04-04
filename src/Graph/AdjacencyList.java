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
        for ( int i = 0; i < numberOfVertices; i++){
            adjacencyList[i] = new LinkedList<Edge>();
            vertices.add(new Vertex(i));
        }
        this.numberOfVertices = numberOfVertices;
    }

    public void print(){
//        Edge tempNode;
        for ( LinkedList<Edge> Edge : adjacencyList){
            System.out.print("Vertex " );
            Edge.forEach((temp) -> {
                        System.out.print("-> " + temp.index);
                    });
//            tempNode = Edge;
//            while ( tempNode != null ) {
//                System.out.print(" " + tempNode.index + " -> ");
//                tempNode = tempNode.nextNode;
//            }
            System.out.println();
        }
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

}

package Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class AdjacencyList {
    public LLNode[] adjacencyList;
    public ArrayList<Vertex> vertices;
    public int numberOfVertices;

    public class LLNode {
        int index;
        int weight;
        LLNode nextNode;
        LLNode parentNode;
        LLNode tailNode;

        public LLNode(int index) {
            this.index = index;
            this.nextNode = null;
            this.parentNode = null;
            this.tailNode = this;
        }

        public LLNode(int index, int weight, LLNode parentNode) {
            this.index = index;
            this.weight = weight;
            this.parentNode = parentNode;
            this.tailNode = this;
            this.nextNode = null;
        }

        public void add(LLNode newNode){
            tailNode.nextNode = newNode;
            tailNode = newNode;
            newNode.parentNode = this;
            newNode.nextNode = null;
        }
    }

    public AdjacencyList(int numberOfVertices) {
        adjacencyList = new LLNode[numberOfVertices];
        for ( int i = 0; i < numberOfVertices; i++){
            adjacencyList[i] = new LLNode(i);
            vertices.add(new Vertex(i));
        }
        this.numberOfVertices = numberOfVertices;
    }

    public void addNode(int source, int destination, int weight){
        adjacencyList[source].add(new LLNode(destination,weight,adjacencyList[source]));
        adjacencyList[destination].add(new LLNode(source,weight,adjacencyList[destination]));
    }

    public void print(){
        LLNode tempNode;
        for ( LLNode llNode : adjacencyList){
            System.out.print("Vertex " );
            tempNode = llNode;
            while ( tempNode != null ) {
                System.out.print(" " + tempNode.index + " -> ");
                tempNode = tempNode.nextNode;
            }
            System.out.println();
        }
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

}

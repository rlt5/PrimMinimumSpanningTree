import Graph.AdjacencyMatrix;
import Graph.Edge;
import Graph.Vertex;
import MinimumSpanningTree.PrimMinimumSpanningTree;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(9);
        adjacencyMatrix.setWeights(0,1,4);
        adjacencyMatrix.setWeights(0,7,8);
        adjacencyMatrix.setWeights(1,7,11);
        adjacencyMatrix.setWeights(1,2,8);
        adjacencyMatrix.setWeights(2,3,7);
        adjacencyMatrix.setWeights(2,5,4);
        adjacencyMatrix.setWeights(2,8,2);
        adjacencyMatrix.setWeights(3, 4, 9);
        adjacencyMatrix.setWeights(3,5,14);
        adjacencyMatrix.setWeights(4,5,10);
        adjacencyMatrix.setWeights(5,6,2);
        adjacencyMatrix.setWeights(6,7,1);
        adjacencyMatrix.setWeights(6,8,6);
        adjacencyMatrix.setWeights(7,8,7);

        adjacencyMatrix.print();
        ArrayList<Edge> edgeList = adjacencyMatrix.getEdgeList();
        for ( int i = 0; i < adjacencyMatrix.numberOfEdges; i++ ){
            System.out.println("Edge: " + edgeList.get(i).vertex1 + "-" + edgeList.get(i).vertex2 + " with Weight: " + edgeList.get(i).weight);
        }

        ArrayList<Vertex> vertices = adjacencyMatrix.getVertices();

        long startTime = System.nanoTime();
        PrimMinimumSpanningTree Prim = new PrimMinimumSpanningTree(adjacencyMatrix,0);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Execution time of Prim is: " + String.format("%.6f", (float)totalTime/1000000) + "ms");

        Prim.minSpanningTree.print();
    }
}

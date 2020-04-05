import Graph.AdjacencyList;
import Graph.AdjacencyMatrix;
import Graph.Edge;
import Graph.Vertex;
import MinimumSpanningTree.PrimMinimumSpanningTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static AdjacencyMatrix randomAdjMatrix(int numberOfVertices, int numberOfEdges, int maxWeight){
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix( numberOfVertices );
        Random rand = new Random();
        int n,m,w;
        for ( int i = 0; i < numberOfVertices; i++){
            n = rand.nextInt(numberOfVertices);
            while ( n == i || adjacencyMatrix.getWeights()[n][i] > 0)
                n = rand.nextInt(numberOfVertices);
            adjacencyMatrix.setWeights(i,n,4);
        }
        numberOfEdges = numberOfEdges - numberOfVertices;
        for  ( ; numberOfEdges > 0; numberOfEdges-- ){
            n = rand.nextInt(numberOfVertices);
            m = rand.nextInt(numberOfVertices);
            w = rand.nextInt(maxWeight);
            while ( n == m || adjacencyMatrix.getWeights()[n][m] > 0)
                m = rand.nextInt(numberOfVertices);
            adjacencyMatrix.setWeights(n,m,w);
        }
        return adjacencyMatrix;
    }

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
//        ArrayList<Edge> edgeList = adjacencyMatrix.getEdgeList();
//        for ( int i = 0; i < adjacencyMatrix.numberOfEdges; i++ ){
//            System.out.println("Edge: " + edgeList.get(i).vertex1 + "-" + edgeList.get(i).vertex2 + " with Weight: " + edgeList.get(i).weight);
//        }

        ArrayList<Vertex> vertices = adjacencyMatrix.getVertices();

        Long startTime = System.nanoTime();
        PrimMinimumSpanningTree Prim = new PrimMinimumSpanningTree(adjacencyMatrix,0);
        Long endTime = System.nanoTime();
        Long totalTime = endTime - startTime;
        System.out.println("Execution time of Prim Matrix is: " + String.format("%.6f", (float)totalTime/1000000) + "ms");

        Prim.minSpanningTree.print();


        AdjacencyList adjacencyList = new AdjacencyList(9);
        adjacencyList.add(0,1,4);
        adjacencyList.add(0,7,8);
        adjacencyList.add(1,7,11);
        adjacencyList.add(1,2,8);
        adjacencyList.add(2,3,7);
        adjacencyList.add(2,5,4);
        adjacencyList.add(2,8,2);
        adjacencyList.add(3,4,9);
        adjacencyList.add(3,5,14);
        adjacencyList.add(4,5,10);
        adjacencyList.add(5,6,2);
        adjacencyList.add(6,7,1);
        adjacencyList.add(6,8,6);
        adjacencyList.add(7,8,7);

        adjacencyList.print();
        startTime = System.nanoTime();
        PrimMinimumSpanningTree PrimLinkedList = new PrimMinimumSpanningTree(adjacencyList,0);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Execution time of Prim LinkedList is: " + String.format("%.6f", (float)totalTime/1000000) + "ms");
        PrimLinkedList.minSpanningTreeList.print();

        ArrayList<Integer> n = new ArrayList<>();
        n.add(100);
//        n.add(200);
//        n.add(400);
//        n.add(800);

        ArrayList<Integer>[] m = new ArrayList[n.size()];
        int x;
        for ( int i = 0; i < n.size(); i++ ){
            x = n.get(i);
            m[i] = new ArrayList<>();
            m[i].add( 3*x);
            m[i].add( (int)( Math.pow(x,1.5)) );
            m[i].add( (int)( x*(x-1)/2) );
        }

        ArrayList<Long>[] timeResults = new ArrayList[n.size()];
        int numberOfVertices;
        for ( int i = 0; i < n.size(); i++ ){
            numberOfVertices = n.get(i);
            timeResults[i] = new ArrayList<>();
            for ( Integer numberOfEdges : m[i] ){
                adjacencyMatrix = randomAdjMatrix(numberOfVertices, numberOfEdges, numberOfVertices/2);
                startTime = System.nanoTime();
                Prim = new PrimMinimumSpanningTree(adjacencyMatrix,0);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                timeResults[i].add(totalTime);
            }

        }
//
//        for ( int i = 0; i < n.size(); i++ ){
//            System.out.print("n = " + n.get(i));
//            for ( Integer y: m[i]){
//                System.out.print( " m = " + y);
//            }
//            System.out.println();
//        }

        for ( int i = 0; i < n.size(); i++ ){
            System.out.print("n = " + n.get(i));
            for ( Long y: timeResults[i]){
                System.out.print( " " + String.format("%.6f", (float)y/1000000) + "ms");
            }
            System.out.println();
        }
    }



}

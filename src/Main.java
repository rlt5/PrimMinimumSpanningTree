import Graph.AdjacencyList;
import Graph.AdjacencyMatrix;
import Graph.Edge;
import Graph.Vertex;
import MinimumSpanningTree.PrimMinimumSpanningTree;

import java.io.FileWriter;
import java.io.IOException;
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
            while ( n == m || adjacencyMatrix.getWeights()[n][m] > 0) {
                m = rand.nextInt(numberOfVertices);
                n = rand.nextInt(numberOfVertices);
            }
            adjacencyMatrix.setWeights(n,m,w);
        }
        return adjacencyMatrix;
    }

    public static AdjacencyList randomAdjList(int numberOfVertices, int numberOfEdges, int maxWeight){
        AdjacencyList adjacencyList = new AdjacencyList( numberOfVertices );
        Random rand = new Random();
        int vertexSrc, vertexDest, weight;

        // Create a connected graph
        for ( vertexSrc = 0; vertexSrc < numberOfVertices; vertexSrc++){
            vertexDest = rand.nextInt(numberOfVertices);
            weight = rand.nextInt(maxWeight);
            while ( vertexSrc == vertexDest || adjacencyList.adjacencyList[vertexSrc].contains(vertexDest) )
                vertexDest = rand.nextInt(numberOfVertices);
            adjacencyList.add(vertexSrc, vertexDest, weight);
        }
        numberOfEdges = numberOfEdges - numberOfVertices;

        // Create random edges
        while ( numberOfEdges > 0 ){
            vertexSrc = rand.nextInt(numberOfVertices);
            vertexDest = rand.nextInt(numberOfVertices);
            weight = rand.nextInt(maxWeight);
            if ( vertexSrc != vertexDest && !adjacencyList.adjacencyList[vertexSrc].contains(vertexDest) ) {
                adjacencyList.add(vertexSrc, vertexDest, weight);
                numberOfEdges--;
            }
        }
        return adjacencyList;
    }

    public static void adjacencyMatrixTest(){
        int iterations = 1000;
        ArrayList<Integer> n = new ArrayList<>();
        n.add(100);
        n.add(200);
        n.add(400);
        n.add(800);

        ArrayList<Integer>[] m = new ArrayList[n.size()];
        int x;
        for ( int i = 0; i < n.size(); i++ ){
            x = n.get(i);
            m[i] = new ArrayList<>();
            m[i].add( 3*x);
            m[i].add( (int)( Math.pow(x,1.5)) );
            m[i].add( x*(x-1)/2 );
        }

        ArrayList<Long>[] timeResults = new ArrayList[n.size()];
        int numberOfVertices;
        for ( int i = 0; i < n.size(); i++ ){
            numberOfVertices = n.get(i);
            timeResults[i] = new ArrayList<>();
            iterations = 1000;
            if ( i < 2 ){
                iterations = 10000;
            }
            for ( Integer numberOfEdges : m[i] ){
                long diffTime;
                totalTime = (long)0;
                for (int iter = 0; iter < iterations; iter++) {
                    AdjacencyMatrix am = randomAdjMatrix(numberOfVertices, numberOfEdges, numberOfVertices / 2);
                    startTime = System.nanoTime();
                    Prim = new PrimMinimumSpanningTree(am, 0);
                    endTime = System.nanoTime();
                    diffTime = endTime - startTime;
                    totalTime += diffTime;
                }
                timeResults[i].add(totalTime/iterations);
            }
        }

        FileWriter writer = new FileWriter("C:\\Users\\robin\\Google Drive\\SFU\\Computing Science\\CMPT 307\\Qianping Gu Spring 2020\\Assignments\\Assignment 7\\primMatrixResults.csv");
        writer.append(String.valueOf("n,3n,n^1,n(n-1)/2\n"));
        for ( int row = 0; row < n.size(); row++ ){
            System.out.print("n = " + n.get(row));
            writer.append(String.valueOf(n.get(row)));
            writer.append(",");
            for ( int col = 0; col < m[0].size(); col++){
                System.out.print( "\t | m = " + String.format("%6d", m[row].get(col)) + String.format("\t%2.3f", (float)timeResults[row].get(col)/1000000) + "ms\t" );
                writer.append(String.format("\t%2.3f", (float)timeResults[row].get(col)/1000000));
                writer.append(",");
            }
            writer.append("\n");
            System.out.println();
        }
        writer.close();
    }


    public static void main(String[] args) throws IOException {
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


//        ArrayList<Long>[] timeResults2 = new ArrayList[n.size()];
//        int numberOfVertices2;
//        for ( int i = 0; i < n.size(); i++ ){
//            numberOfVertices2 = n.get(i);
//            timeResults2[i] = new ArrayList<>();
//            for ( Integer numberOfEdges : m[i] ){
//                long diffTime;
//                totalTime = (long)0;
//                for (int iter = 0; iter < iterations; iter++) {
//                    AdjacencyList am = randomAdjList(numberOfVertices2, numberOfEdges, numberOfVertices2 / 2);
//                    startTime = System.nanoTime();
//                    Prim = new PrimMinimumSpanningTree(am, 0);
//                    endTime = System.nanoTime();
//                    diffTime = endTime - startTime;
//                    totalTime += diffTime;
//                }
//                timeResults2[i].add(totalTime/iterations);
//            }
//        }
//
//        writer = new FileWriter("C:\\Users\\robin\\Google Drive\\SFU\\Computing Science\\CMPT 307\\Qianping Gu Spring 2020\\Assignments\\Assignment 7\\primListResults.csv");
//        writer.append(String.valueOf("n,3n,n^1,n(n-1)/2\n"));
//        for ( int row = 0; row < n.size(); row++ ){
//            System.out.print("n = " + n.get(row));
//            writer.append(String.valueOf(n.get(row)));
//            writer.append(",");
//            for ( int col = 0; col < m[0].size(); col++){
//                System.out.print( "\t | m = " + String.format("%6d", m[row].get(col)) + String.format("\t%2.3f", (float)timeResults2[row].get(col)/1000000) + "ms\t" );
//                writer.append(String.format("\t%2.3f", (float)timeResults2[row].get(col)/1000000));
//                writer.append(",");
//            }
//            writer.append("\n");
//            System.out.println();
//        }
//        writer.close();
    }
}

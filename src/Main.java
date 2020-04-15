import BellmanFord.BellmanFord;
import Graph.AdjacencyList;
import Graph.AdjacencyMatrix;
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
        int vertexSrc, vertexDest, weight;

        // Create a connected graph
        for ( vertexSrc = 0; vertexSrc < numberOfVertices; vertexSrc++){
            vertexDest = rand.nextInt(numberOfVertices);
            while ( vertexSrc == vertexDest || adjacencyMatrix.getWeights()[vertexSrc][vertexDest] > 0)
                vertexDest = rand.nextInt(numberOfVertices);
            adjacencyMatrix.setUndirectedWeights(vertexSrc, vertexDest,4);
        }
        numberOfEdges = numberOfEdges - numberOfVertices;

        // Create random edges
        while ( numberOfEdges > 0 ){
            vertexSrc = rand.nextInt(numberOfVertices);
            vertexDest = rand.nextInt(numberOfVertices);
            if ( vertexSrc != vertexDest && adjacencyMatrix.getWeights()[vertexSrc][vertexDest] == 0) {
                weight = rand.nextInt(maxWeight);
                adjacencyMatrix.setUndirectedWeights(vertexSrc, vertexDest, weight);
                numberOfEdges--;
            }
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
            if ( vertexSrc != vertexDest && !adjacencyList.adjacencyList[vertexSrc].contains(vertexDest) ) {
                weight = rand.nextInt(maxWeight);
                adjacencyList.add(vertexSrc, vertexDest, weight);
                numberOfEdges--;
            }
        }
        return adjacencyList;
    }

    public static ArrayList<Long>[] adjacencyMatrixTest(int iterations,
                                           ArrayList<Integer> vertices,
                                           ArrayList<Integer>[] edges,
                                           String fileName) throws IOException {
        long totalTime, startTime, endTime, diffTime;
        ArrayList<Long>[] timeResults = new ArrayList[vertices.size()];
        int numberOfVertices;
        for ( int i = 0; i < vertices.size(); i++ ){
            numberOfVertices = vertices.get(i);
            timeResults[i] = new ArrayList<>();
            for ( Integer numberOfEdges : edges[i] ){
                totalTime = (long)0;
                for (int iter = 0; iter < iterations; iter++) {
                    AdjacencyMatrix am = randomAdjMatrix(numberOfVertices, numberOfEdges, numberOfVertices / 2);
                    startTime = System.nanoTime();
                    PrimMinimumSpanningTree Prim = new PrimMinimumSpanningTree(am, 0);
                    endTime = System.nanoTime();
                    diffTime = endTime - startTime;
                    totalTime += diffTime;
                }
                timeResults[i].add(totalTime/iterations);
            }
        }

        FileWriter writer = new FileWriter( fileName );
//        writer.append(String.valueOf("n,3n,n^1,n(n-1)/2\n"));
        for ( int row = 0; row < vertices.size(); row++ ){
//            System.out.print("n = " + vertices.get(row));
            writer.append(String.valueOf(vertices.get(row)));
            writer.append(",");
            for ( int col = 0; col < edges[0].size(); col++){
//                System.out.print( "\t | m = " + String.format("%6d", edges[row].get(col)) + String.format("\t%2.3f", (float)timeResults[row].get(col)/1000000) + "ms\t" );
                writer.append(String.format("\t%2.3f", (float)timeResults[row].get(col)/1000000));
                writer.append(",");
            }
            writer.append("\n");
//            System.out.println();
        }
        writer.close();
        return timeResults;
    }

    public static ArrayList<Long>[] adjacencyListTest(int iterations,
                                         ArrayList<Integer> vertices,
                                         ArrayList<Integer>[] edges,
                                         String fileName) throws IOException {
        long totalTime, startTime, endTime, diffTime;
        ArrayList<Long>[] timeResults = new ArrayList[vertices.size()];
        int numberOfVertices;
        for ( int i = 0; i < vertices.size(); i++ ){
            numberOfVertices = vertices.get(i);
            timeResults[i] = new ArrayList<>();
            for ( Integer numberOfEdges : edges[i] ){
                totalTime = (long)0;
                for (int iter = 0; iter < iterations; iter++) {
                    AdjacencyList am = randomAdjList(numberOfVertices, numberOfEdges, numberOfVertices / 2);
                    startTime = System.nanoTime();
                    PrimMinimumSpanningTree Prim = new PrimMinimumSpanningTree(am, 0);
                    endTime = System.nanoTime();
                    diffTime = endTime - startTime;
                    totalTime += diffTime;
                }
                timeResults[i].add(totalTime/iterations);
            }
        }
        FileWriter writer = new FileWriter( fileName );
        for ( int row = 0; row < vertices.size(); row++ ){
//            System.out.print("n = " + vertices.get(row));
            writer.append(String.valueOf(vertices.get(row)));
            writer.append(",");
            for ( int col = 0; col < edges[0].size(); col++){
//                System.out.print( "\t | m = " + String.format("%6d", edges[row].get(col)) + String.format("\t%2.3f", (float)timeResults[row].get(col)/1000000) + "ms\t" );
                writer.append(String.format("\t%2.3f", (float)timeResults[row].get(col)/1000000));
                writer.append(",");
            }
            writer.append("\n");
//            System.out.println();
        }
        writer.close();
        return timeResults;
    }

    public static void main(String[] args) throws IOException {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(9);
        adjacencyMatrix.setUndirectedWeights(0,1,4);
        adjacencyMatrix.setUndirectedWeights(0,7,8);
        adjacencyMatrix.setUndirectedWeights(1,7,11);
        adjacencyMatrix.setUndirectedWeights(1,2,8);
        adjacencyMatrix.setUndirectedWeights(2,3,7);
        adjacencyMatrix.setUndirectedWeights(2,5,4);
        adjacencyMatrix.setUndirectedWeights(2,8,2);
        adjacencyMatrix.setUndirectedWeights(3, 4, 9);
        adjacencyMatrix.setUndirectedWeights(3,5,14);
        adjacencyMatrix.setUndirectedWeights(4,5,10);
        adjacencyMatrix.setUndirectedWeights(5,6,2);
        adjacencyMatrix.setUndirectedWeights(6,7,1);
        adjacencyMatrix.setUndirectedWeights(6,8,6);
        adjacencyMatrix.setUndirectedWeights(7,8,7);

        adjacencyMatrix.print();
//        ArrayList<Edge> edgeList = adjacencyMatrix.getEdgeList();
//        for ( int i = 0; i < adjacencyMatrix.numberOfEdges; i++ ){
//            System.out.println("Edge: " + edgeList.get(i).vertex1 + "-" + edgeList.get(i).vertex2 + " with Weight: " + edgeList.get(i).weight);
//        }

//        ArrayList<Vertex> vertices = adjacencyMatrix.getVertices();

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

        int iterations = 1000;
        ArrayList<Integer> vertices = new ArrayList<>();
//        vertices.add(100);
//        vertices.add(200);
//        vertices.add(400);
//        vertices.add(800);

        ArrayList<Integer>[] edges = new ArrayList[vertices.size()];
        int tempVertex;
        for ( int i = 0; i < vertices.size(); i++ ){
            tempVertex = vertices.get(i);
            edges[i] = new ArrayList<>();
            edges[i].add( 3*tempVertex);
            edges[i].add( (int)( Math.pow(tempVertex,1.5)) );
            edges[i].add( tempVertex*(tempVertex-1)/2 );
        }
        String fileName = "C:\\Users\\robin\\Google Drive\\SFU\\Computing Science\\CMPT 307\\Qianping Gu Spring 2020\\Assignments\\Assignment 7\\primMatrixResults.csv";
        ArrayList<Long>[] timeResults = adjacencyMatrixTest(10, vertices, edges, fileName );

        fileName = "C:\\Users\\robin\\Google Drive\\SFU\\Computing Science\\CMPT 307\\Qianping Gu Spring 2020\\Assignments\\Assignment 7\\primListResults.csv";
        ArrayList<Long>[] timeResultsList = adjacencyListTest(1, vertices, edges, fileName );

        AdjacencyMatrix adjacencyMatrixShortestPath = new AdjacencyMatrix(5);
        adjacencyMatrixShortestPath.setDiGraphWeights(0,1,3);
        adjacencyMatrixShortestPath.setDiGraphWeights(0,3,5);
        adjacencyMatrixShortestPath.setDiGraphWeights(1,2,6);
        adjacencyMatrixShortestPath.setDiGraphWeights(1,3,2);
        adjacencyMatrixShortestPath.setDiGraphWeights(2,4,2);
        adjacencyMatrixShortestPath.setDiGraphWeights(3,1,1);
        adjacencyMatrixShortestPath.setDiGraphWeights(3,2,4);
        adjacencyMatrixShortestPath.setDiGraphWeights(3,4,6);
        adjacencyMatrixShortestPath.setDiGraphWeights(4,0,3);
        adjacencyMatrixShortestPath.setDiGraphWeights(4,2,7);

        adjacencyMatrixShortestPath.print();

        BellmanFord bellmanFord = new BellmanFord(adjacencyMatrixShortestPath, 0);
        bellmanFord.print();

        AdjacencyMatrix adjacencyMatrixShortestPathTextbook652 = new AdjacencyMatrix(5);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(0,1,6);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(0,3,7);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(1,2,5);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(1,3,8);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(1,4,-4);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(2,1,-2);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(3,2,-3);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(3,4,9);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(4,0,2);
        adjacencyMatrixShortestPathTextbook652.setDiGraphWeights(4,2,7);
        adjacencyMatrixShortestPathTextbook652.print();

        BellmanFord bellmanFord652 = new BellmanFord(adjacencyMatrixShortestPathTextbook652, 0);
        bellmanFord652.print();

    }
}

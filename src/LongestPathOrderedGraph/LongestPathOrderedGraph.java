package LongestPathOrderedGraph;

import Graph.AdjacencyMatrix;

public class LongestPathOrderedGraph {
    public int longestPath;
    public static int count;
    public LongestPathOrderedGraph(AdjacencyMatrix adjacencyMatrix){

        int[] m = new int[adjacencyMatrix.numberOfVertices];
        int[][] weights = adjacencyMatrix.getWeights();
        for (int i = 0; i < adjacencyMatrix.numberOfVertices; i++){
            for (int j = i+1; j < adjacencyMatrix.numberOfVertices; j++ ){
                if ( m[j] < m[i] + weights[i][j] ) {
                    m[j] = m[i] + weights[i][j];
                }
                count = count + 1;
            }
        }
        for (int i = 0; i < adjacencyMatrix.numberOfVertices; i++){
            System.out.println(i + " = " + m[i]);
        }
        longestPath = m[adjacencyMatrix.numberOfVertices-1];
        System.out.println("longest path = " + longestPath);
    }


}

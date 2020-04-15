package BellmanFord;

import Graph.AdjacencyList;
import Graph.AdjacencyMatrix;
import Graph.Vertex;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class BellmanFord {

    AdjacencyMatrix adjacencyMatrix;

    public BellmanFord(AdjacencyMatrix adjacencyMatrix, int root){
        this.adjacencyMatrix = adjacencyMatrix;
        if (!shortestPath(adjacencyMatrix, root))
            System.out.println("===== FAILED - Negative cycles are reachable from source node s =====");
    }

    public Boolean shortestPath(AdjacencyMatrix graph, int root){
        initializeSingleSource(graph, root);
        ArrayList<Vertex> vertices = graph.getVertices();
        Stack<Vertex> stack = new Stack<Vertex>();
        stack.push(vertices.get(root));
        Vertex u;
        int[][] weights = graph.getWeights();
        System.out.println("=====Iteration 0 =====");
        this.print();
        for ( int i = 1; i < graph.numberOfVertices; i++){
            System.out.println("=====Iteration " + i + " =====");
            Stack<Vertex> newStack = new Stack<Vertex>();
            while ( !stack.isEmpty() ){
                u = stack.pop();
                for (Vertex v : vertices){
                    if ( weights[u.index][v.index] != 0 ) {
                        if ( relax(weights,u,v) ){
                            newStack.push(v);
                        }
                    }
                }
            }
            stack = newStack;
            this.print();
        }
        System.out.println("======================");
        System.out.println();
        for ( Vertex u2 : vertices){
            for (Vertex v : vertices){
                if ( weights[u2.index][v.index] > 0 ) {
                    if ( v.key > u2.key + weights[u2.index][v.index]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void initializeSingleSource(AdjacencyMatrix graph, int root){
        ArrayList<Vertex> vertices = graph.getVertices();
        for (Vertex u : vertices){
            u.setKey(Integer.MAX_VALUE);
            u.parent = null;
        }
        vertices.get(root).setKey(0);
    }
    public Boolean relax(int[][] weights, Vertex u, Vertex v){
        if (v.key > u.key + weights[u.index][v.index]) {
            v.key = u.key + weights[u.index][v.index];
            v.parent = u;
            return true;
        }
        return false;
    }

    public void print(){
        ArrayList<Vertex> vertices = adjacencyMatrix.getVertices();
        Stack<Vertex> vertexStack = new Stack<>();
        int[][] weights = adjacencyMatrix.getWeights();
        for ( Vertex vertex : vertices ){
            System.out.printf("Total: " + vertex.key  + "\t");
            while ( vertex != null ){
                vertexStack.push(vertex);
                vertex = vertex.parent;
            }
            Vertex tempVertex;
            while ( !vertexStack.isEmpty() ){
                tempVertex = vertexStack.pop();
                System.out.printf(tempVertex.index + "");
                if ( !vertexStack.isEmpty()){
                    System.out.printf("(" + weights[tempVertex.index][vertexStack.peek().index] +")" + "->");
                }
            }

            System.out.println();
        }
    }

}

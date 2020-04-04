package MinimumSpanningTree;

import Graph.AdjacencyList;
import Graph.AdjacencyMatrix;
import Graph.Vertex;

import java.lang.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimMinimumSpanningTree {
    public AdjacencyMatrix minSpanningTree;
    public AdjacencyList minSpanningTreeList;

    public PrimMinimumSpanningTree(AdjacencyMatrix graph, int root) {
        ArrayList<Vertex> vertices = graph.getVertices();
        int[][] weights = graph.getWeights();
        for (Vertex u : vertices){
            u.setKey(Integer.MAX_VALUE);
            u.parent = null;
        }
        vertices.get(root).setKey(0);
//        ArrayList<Vertex> visitedVertices = new ArrayList<>();
        PriorityQueue<Vertex> Q = new PriorityQueue<>(new Vertex.VertexComparator());
        Q.addAll(vertices);
        Vertex u;
        while ( !Q.isEmpty() ){
            u = Q.poll();
            for (Vertex v : vertices){
                if ( weights[v.index][u.index] > 0 ){
                    if ( Q.contains(v) && weights[u.index][v.index] < v.getKey() ){
                        Q.remove(v);
                        v.parent = u;
                        v.setKey(weights[u.index][v.index]);
                        Q.add(v);
                    }
                }
            }
        }
        this.minSpanningTree = new AdjacencyMatrix(graph.numberOfVertices);
        for ( int i = 1; i < graph.numberOfVertices; i++){ // i starts at 1 because node 0/root has no parent
            this.minSpanningTree.setWeights(i,vertices.get(i).parent.index,weights[i][vertices.get(i).parent.index]);
        }
    }

    public PrimMinimumSpanningTree(AdjacencyList list, int root) {
        ArrayList<Vertex> vertices = list.getVertices();
//        int[][] weights = graph.getWeights();
        for (Vertex u : vertices){
            u.setKey(Integer.MAX_VALUE);
            u.parent = null;
        }
        vertices.get(root).setKey(0);
//        ArrayList<Vertex> visitedVertices = new ArrayList<>();
        PriorityQueue<Vertex> Q = new PriorityQueue<>(new Vertex.VertexComparator());
        for (Vertex u : vertices){
            Q.add(u);
        }
        Vertex u;
        while ( !Q.isEmpty() ){
            u = Q.poll();
            for (Vertex v : vertices){
                if ( weights[v.index][u.index] > 0 ){
                    if ( Q.contains(v) && weights[u.index][v.index] < v.getKey() ){
                        Q.remove(v);
                        v.parent = u;
                        v.setKey(weights[u.index][v.index]);
                        Q.add(v);
                    }
                }
            }
        }
        this.minSpanningTree = new AdjacencyMatrix(graph.numberOfVertices);
        for ( int i = 1; i < graph.numberOfVertices; i++){ // i starts at 1 because node 0/root has no parent
            this.minSpanningTree.setWeights(i,vertices.get(i).parent.index,weights[i][vertices.get(i).parent.index]);
        }
    }
}

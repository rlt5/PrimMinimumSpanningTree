package MinimumSpanningTree;

import Graph.AdjacencyList;
import Graph.AdjacencyMatrix;
import Graph.Edge;
import Graph.Vertex;

import java.lang.*;
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
        PriorityQueue<Vertex> Q = new PriorityQueue<>(new Vertex.VertexComparator());
        Q.addAll(vertices);
        Vertex u;
        int uIndex,vIndex;
        while ( !Q.isEmpty() ){
            u = Q.poll();
            uIndex = u.index;
            for (Vertex v : vertices){
                vIndex = v.index;
                if ( weights[vIndex][uIndex] > 0 ){
                    if ( Q.contains(v) && weights[uIndex][vIndex] < v.getKey() ){
                        Q.remove(v);
                        v.parent = u;
                        v.setKey(weights[uIndex][vIndex]);
                        Q.add(v);
                    }
                }
            }
        }
        this.minSpanningTree = new AdjacencyMatrix(graph.numberOfVertices);
        int parentIndex;
        for ( int i = 1; i < graph.numberOfVertices; i++){ // i starts at 1 because node 0/root has no parent
            parentIndex = vertices.get(i).parent.index;
            this.minSpanningTree.setUndirectedWeights(i,parentIndex,weights[i][parentIndex]);
        }
    }

    public PrimMinimumSpanningTree(AdjacencyList graph, int root) {
        ArrayList<Vertex> vertices = graph.getVertices();
        int j = 0;
        for (Vertex u : vertices){
            u.setKey(Integer.MAX_VALUE);
            u.parent = null;
            u.index = j;
            j++;
        }
        vertices.get(root).setKey(0);
        PriorityQueue<Vertex> Q = new PriorityQueue<>(new Vertex.VertexComparator());
        for (Vertex u : vertices){
            Q.add(u);
        }
        Vertex u;
        while ( !Q.isEmpty() ){
            u = Q.poll();
            for (Edge edge : graph.adjacencyList[u.index]){
                if ( Q.contains(vertices.get(edge.index)) && edge.weight < vertices.get(edge.index).key ){
                        Q.remove(vertices.get(edge.index));
                        vertices.get(edge.index).parent = u;
                        vertices.get(edge.index).key = edge.weight;
                        Q.add(vertices.get(edge.index));
                }
            }
        }

        this.minSpanningTreeList = new AdjacencyList(graph.numberOfVertices);
        for ( int i = 1; i < graph.numberOfVertices; i++){ // i starts at 1 because node 0/root has no parent
            this.minSpanningTreeList.add(vertices.get(i).parent.index, i, vertices.get(i).key);
        }
    }
}

package Graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix {
    public int numberOfVertices;
    private int[][] weights;
    public int numberOfEdges;
    public ArrayList<Vertex> vertices;

    public AdjacencyMatrix(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.weights = new int[numberOfVertices][numberOfVertices];
        this.vertices = new ArrayList<>(numberOfVertices);
        for ( int i = 0; i < numberOfVertices; i++){
            vertices.add(new Vertex(i));
        }
    }

    public int[][] getWeights() {
        return weights;
    }

    public void setWeights(int index1, int index2, int weight) {
        this.weights[index1][index2] = weight;
        this.weights[index2][index1] = weight;
        this.numberOfEdges++;
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

//    public void setVertices(ArrayList<Vertex> vertices) {
//        this.vertices = vertices;
//    }

    public void print(){
        for (int row = 0; row < numberOfVertices; row++ ){
            for (int col = 0; col < numberOfVertices; col++ ){
                System.out.printf(" %2d" ,weights[row][col]);
            }
            System.out.println();
        }
    }

    public ArrayList<Edge> getEdgeList(){
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int row = 0; row < numberOfVertices; row++ ){
            for (int col = row; col < numberOfVertices; col++ ){
                if (weights[row][col] != 0){
                    edgeList.add(new Edge(row,col,weights[row][col]));
                }
            }
        }
        return edgeList;
    }
}

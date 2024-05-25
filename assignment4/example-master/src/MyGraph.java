import java.util.*;

public class MyGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public MyGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addEdge(V source, V dest) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, Vertex::new);
        sourceVertex.addAdjacentVertex(destVertex, 1.0);
        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, 1.0);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }
}

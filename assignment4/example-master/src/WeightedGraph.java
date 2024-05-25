import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private final boolean directed;
    private final Map<V, Vertex<V>> vertices;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> srcVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, Vertex::new);
        srcVertex.addAdjacentVertex(destVertex, weight);
        if (!directed) {
            destVertex.addAdjacentVertex(srcVertex, weight);
        }
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }

    public Vertex<V> getVertex(V key) {
        return vertices.get(key);
    }
}

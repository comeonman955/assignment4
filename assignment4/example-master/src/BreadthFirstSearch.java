import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo;
    private Set<V> marked;
    private final V source;

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        this.source = source;
        edgeTo = new HashMap<>();
        marked = new HashSet<>();
        bfs(graph, source);
    }

    private void bfs(MyGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);
        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (Vertex<V> w : graph.getVertex(v).getAdjacentVertices().keySet()) {
                if (!marked.contains(w.getData())) {
                    queue.add(w.getData());
                    marked.add(w.getData());
                    edgeTo.put(w.getData(), v);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V v) {
        return marked.contains(v);
    }

    @Override
    public List<V> pathTo(V v) {
        if (!hasPathTo(v)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V x = v; !x.equals(source); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(source);
        return path;
    }
}



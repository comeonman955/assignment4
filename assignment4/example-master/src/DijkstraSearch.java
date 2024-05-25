import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, Double> distTo;
    private final Map<V, V> edgeTo;
    private final Set<V> settledNodes;
    private final PriorityQueue<V> priorityQueue;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        settledNodes = new HashSet<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));

        // Initialize distances to infinity and source to 0
        for (V vertex : graph.getVertices().keySet()) {
            distTo.put(vertex, Double.MAX_VALUE);
        }
        distTo.put(source, 0.0);

        // Add source to the priority queue
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            V u = priorityQueue.poll();
            if (!settledNodes.contains(u)) {
                settledNodes.add(u);
                relaxNeighbors(graph, u);
            }
        }
    }

    private void relaxNeighbors(WeightedGraph<V> graph, V u) {
        for (Map.Entry<Vertex<V>, Double> entry : graph.getVertex(u).getAdjacentVertices().entrySet()) {
            Vertex<V> v = entry.getKey();
            Double weight = entry.getValue();
            if (!settledNodes.contains(v.getData())) {
                double newDist = distTo.get(u) + weight;
                if (newDist < distTo.get(v.getData())) {
                    distTo.put(v.getData(), newDist);
                    edgeTo.put(v.getData(), u);
                    priorityQueue.add(v.getData());
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V v) {
        return distTo.containsKey(v) && distTo.get(v) < Double.MAX_VALUE;
    }

    @Override
    public List<V> pathTo(V v) {
        if (!hasPathTo(v)) return null;
        List<V> path = new ArrayList<>();
        for (V x = v; x != null; x = edgeTo.get(x)) {
            path.add(x);
        }
        Collections.reverse(path);
        return path;
    }
}

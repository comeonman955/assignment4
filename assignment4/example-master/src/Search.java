import java.util.List;

public interface Search<V> {
    boolean hasPathTo(V v);
    List<V> pathTo(V v);
}

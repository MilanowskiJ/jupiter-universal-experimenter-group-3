import java.util.Map;

public interface DatabaseLinker<T> {
    Map<String, T> getModels();
    boolean add(T model);
    boolean update(T model);
    boolean delete(T model);
}

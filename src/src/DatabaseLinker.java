import java.util.List;

public interface DatabaseLinker<T> {
    List<T> getModels();
    boolean add(T model);
    boolean update(T model);
    boolean delete(int id);
}

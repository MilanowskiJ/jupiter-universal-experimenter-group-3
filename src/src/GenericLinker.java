import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenericLinker<T extends DatabaseModel> implements DatabaseLinker<T>{
    String connectionUrl;
    String modelGetQuery;
    ResultManager<T> modelResult;

    public GenericLinker(String connectionUrl,
                         String modelGetQuery,
                         ResultManager<T> modelResult){
        this.connectionUrl = connectionUrl;
        this.modelGetQuery = modelGetQuery;
        this.modelResult = modelResult;
    }

    @Override
    public List<T> getModels() {
        ArrayList<T> modelList = new ArrayList<>();
        ResultSet results;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()) {

            results = statement.executeQuery(modelGetQuery);

            while (results.next()) {
                modelList.add(modelResult.processResult(results));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return modelList;
    }

    @Override
    public boolean add(T model) {
        ResultSet results;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(model.addQuery())) {

            preparedStatement.execute();
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(T model) {
        ResultSet results;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(model.updateQuery())) {

            preparedStatement.executeUpdate();
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(T model) {
        ResultSet results;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(model.deleteQuery())) {

            preparedStatement.executeUpdate();
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public interface ResultManager<G>{
        G processResult(ResultSet result) throws SQLException;
    }

    public interface ModelManager<G>{
        String processModel(G model);
    }
}

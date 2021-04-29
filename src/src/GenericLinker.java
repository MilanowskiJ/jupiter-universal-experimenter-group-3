import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenericLinker<T> implements DatabaseLinker<T>{
    String connectionUrl;
    String modelGetQuery;
/*  String modelUpdateQuery;
    String modelDeleteQuery;*/
    ResultManager<T> modelResult;
    ModelManager<T> modelAddQueryCreator;

    public GenericLinker(String connectionUrl,
                         String modelGetQuery,
                         /*String modelUpdateQuery,
                         String modelDeleteQuery,*/
                         ResultManager<T> modelResult,
                         ModelManager<T> modelAddQueryCreator){
        this.connectionUrl = connectionUrl;
        this.modelGetQuery = modelGetQuery;
        /*this.modelUpdateQuery = modelUpdateQuery;
        this.modelDeleteQuery = modelDeleteQuery;*/
        this.modelResult = modelResult;
        this.modelAddQueryCreator = modelAddQueryCreator;
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
             PreparedStatement preparedStatement = connection.prepareStatement(modelAddQueryCreator.processModel(model));) {

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
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public interface ResultManager<G>{
        G processResult(ResultSet result) throws SQLException;
    }

    public interface ModelManager<G>{
        String processModel(G model);
    }

    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://yourserver.database.windows.net:1433;"
                        + "database=AdventureWorks;"
                        + "user=yourusername@yourserver;"
                        + "password=yourpassword;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        String insertSql = "INSERT INTO SalesLT.Product (Name, ProductNumber, Color, StandardCost, ListPrice, SellStartDate) VALUES "
                + "('NewBike', 'BikeNew', 'Blue', 50, 120, '2016-01-01');";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

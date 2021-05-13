package data;

import business.models.Macro;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MacroLinker implements DatabaseLinker<Macro>{
    private String connectionUrl;
    private final String macroGetQuery =
            "SELECT Snackros.[CommandID], [MacroName], [Order], Snackros.[Params], CommandName, Commands.Params " +
                    "FROM [Snackros] JOIN Commands ON Snackros.CommandID = Commands.CommandID";

    public MacroLinker(String connectionUrl){
        this.connectionUrl = connectionUrl;
    }

    @Override
    public Map getModels() {
        HashMap<String, Macro> modelList = new HashMap<>();
        ResultSet results;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            results = statement.executeQuery(macroGetQuery);

            while (results.next()) {
                String macroName = results.getString("MacroName");
                if(modelList.containsKey(macroName)){
                    Macro temp = modelList.get(macroName);
                }else{
                    Macro temp = new Macro("macroName");
                    modelList.put(temp.getDatabaseID(), temp);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return modelList;
    }

    @Override
    public boolean add(Macro model) {
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
    public boolean update(Macro model) {
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
    public boolean delete(Macro model) {
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
}

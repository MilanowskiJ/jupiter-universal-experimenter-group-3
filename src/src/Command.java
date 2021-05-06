import java.sql.ResultSet;
import java.sql.SQLException;

public class Command implements DatabaseModel{
    private String ID;
    private String name;
    private String parameters;

    public Command(String ID, String name, String parameters) {
        this.ID = ID;
        this.name = name;
        this.parameters = parameters;
    }

    @Override
    public String getQuery() {
        return null;
    }

    @Override
    public String addQuery() {
        return null;
    }

    @Override
    public String updateQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        return null;
    }

    @Override
    public String getDatabaseID() {
        return ID;
    }

    @Override
    public void processResult(ResultSet result) throws SQLException {
    }
}

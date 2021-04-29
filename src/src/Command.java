import java.sql.ResultSet;
import java.sql.SQLException;

public class Command implements DatabaseModel{
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
    public void processResult(ResultSet result) throws SQLException {
    }
}

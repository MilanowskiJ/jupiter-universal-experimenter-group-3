import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseModel {
    String addQuery();
    String updateQuery();
    String deleteQuery();
    void processResult(ResultSet result) throws SQLException;
}

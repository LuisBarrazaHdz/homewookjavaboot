package expeditors.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {
    protected Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String username = "larku";
        String password = "larku";

        return DriverManager.getConnection(url, username, password);
    }
}

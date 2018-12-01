package databaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionFactory {

    private static final String username = "root";
    private static final String password = "root";
    private static final String dbSchemaURL = "jdbc:mysql://localhost:3306/VEHICLESINSURANCECHECK?autoReconnect=true&useSSL=false";
    private static final String dbURL = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection (dbSchemaURL, username, password);

    }

    public static Connection getConnectionWithoutSchema() throws SQLException {
        return DriverManager.getConnection (dbURL, username, password);

    }
}

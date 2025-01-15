package dbconnection;
import java.sql.*;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade","root","1234");
    }

    public static DBConnection getInstance() throws SQLException {
        return instance!=null?instance:new DBConnection();
    }

    public Connection getConnection(){
        return connection;
    }
}

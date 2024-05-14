package Practice;

import java.sql.*;

public class Jdbc {
    private final String URL;
    private final String user;
    private final String password;
    private Connection conn;
    private Statement stmt;


    public Jdbc(String URL, String user, String password) {
        this.URL = URL;
        this.user = user;
        this.password = password;
    }

    void connect(Jdbc jdbc) throws SQLException {
        conn = DriverManager.getConnection(jdbc.URL, jdbc.user, jdbc.password);
        stmt = conn.createStatement();
    }

    boolean executeDB(String sql) throws SQLException {
        return stmt.execute(sql);
    }
    int executeUpdate(String sql) throws SQLException{
        return stmt.executeUpdate(sql);
    }

    ResultSet executeQuery(String sql) throws SQLException{
        return stmt.executeQuery(sql);
    }


    void close() throws SQLException {
        stmt.close();
        conn.close();
    }

}

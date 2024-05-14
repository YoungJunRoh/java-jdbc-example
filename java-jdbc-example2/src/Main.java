import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try{
            String URL = "jdbc:mysql://127.0.0.1:3306";
            String user = "root";
            String password = "asdasd1";
            Connection conn = DriverManager.getConnection(URL,user,password);
            Statement stmt = conn.createStatement();

            String sql  = "CREATE DATABASE IF NOT EXISTS testDatabase";
            stmt.execute(sql);
            System.out.println("데이터베이스가 생성되었습니다.");

            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();;
        }
    }
}
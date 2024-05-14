import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    public static void main(String[] args) {
        try {
            String URL = "jdbc:mysql://127.0.0.1:3306/testDatabase";
            String user = "root";
            String password = "asdasd1";
            Connection conn = DriverManager.getConnection(URL, user, password);

            Statement stmt = conn.createStatement();

            String SQL = "CREATE TABLE IF NOT EXISTS Board (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "title VARCHAR(255) NOT NULL," +
                    "body TEXT NOT NULL)";
            stmt.executeUpdate(SQL);
            System.out.println("Board 테이블을 생성했습니다.");

            SQL = "CREATE TABLE IF NOT EXISTS Member (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL UNIQUE," +
                    "board_id INT," +
                    "FOREIGN KEY (board_id) REFERENCES Board(id))";
            stmt.executeUpdate(SQL);
            System.out.println("Member 테이블을 생성했습니다.");


            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            ;
        }
    }
}

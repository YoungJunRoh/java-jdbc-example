package Practice;
import java.sql.SQLException;

public class CreateDatabase {
    public static void main(String[] args) throws SQLException {
        Jdbc jdbc = new Jdbc("jdbc:mysql://127.0.0.1:3306",
                "root", "asdasd1");
        jdbc.connect(jdbc);


        String sql  = "CREATE DATABASE IF NOT EXISTS Instagram";
        jdbc.executeDB(sql);
        System.out.println("데이터베이스가 생성되었습니다.");

        jdbc.close();

    }
}

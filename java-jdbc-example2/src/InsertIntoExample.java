import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoExample {
    public static void main(String[] args) {
        try{
            String URL = "jdbc:mysql://127.0.0.1:3306/testDatabase";
            String user = "root";
            String password = "asdasd1";
            Connection conn = DriverManager.getConnection(URL,user,password);

            Statement stmt = conn.createStatement();

            String SQL = " INSERT INTO Board (title, body) VALUES " +
                    "('Java 프로그래밍','Java프로그래밍에 대한 기초')," +
                    "('데이터베이스 기초', 'SQL 데이터베이스 관리')," +
                    "('데이터베이스 심화', 'SQL 및 데이터베이스 관리에 대한 입문서')," +
                    "('C++' , 'C++에 대한 기초')";
            int boardRows = stmt.executeUpdate(SQL);
            System.out.println(boardRows + "개의 글이 Board 테이블에 생성되었습니다");

            SQL = "INSERT INTO Member (name, email, board_id) VALUES " +
                    "('김철수', 'chulsoo@example.com', 1), " +
                    "('이영희', 'younghee@example.com', 2)," +
                    "('노영준', 'youngjun@example.com', 3)," +
                    "('홍길동', 'hongkil@example.com', 4)";
            boardRows = stmt.executeUpdate(SQL);
            System.out.println(boardRows + "개의 글이 Member 테이블에 생성되었습니다.");

            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();;
        }
    }
}

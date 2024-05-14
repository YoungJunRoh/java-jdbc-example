import java.sql.*;

public class Example {
    public static void main(String[] args) {
        try{
            String URL = "jdbc:mysql://127.0.0.1:3306/testDatabase";
            String user = "root";
            String password = "asdasd1";
            Connection conn = DriverManager.getConnection(URL,user,password);
            Statement stmt = conn.createStatement();

            String selectBoardSQL = "SELECT * FROM Board;";
            ResultSet rsBoard = stmt.executeQuery(selectBoardSQL);

            while(rsBoard.next()){
                System.out.println("ID : " + rsBoard.getInt("id")+
                        " 제목 : " + rsBoard.getString("title") +
                        " 내용 : " + rsBoard.getString("body"));
            }
            String deleteMemberSQL = "DELETE FROM Member WHERE " +
                    "name = '홍길동'";
            int deleteRows = stmt.executeUpdate(deleteMemberSQL);
            System.out.println(deleteRows + " 명의 회원이 삭제되었습니다.");

            String updateBoardSQL = "UPDATE Board SET title = '안드로이드' " +
                    "WHERE id = 1";
            int updateRows = stmt.executeUpdate(updateBoardSQL);
            System.out.println(updateRows + " 개의 글 제목이 변경되었습니다.");



            rsBoard.close();
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();;
        }
    }
}

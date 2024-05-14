package Practice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Practice {
    public static void main(String[] args) throws SQLException {
        Jdbc jdbc = new Jdbc("jdbc:mysql://127.0.0.1:3306/Instagram",
                "root", "asdasd1");
        jdbc.connect(jdbc);

        // 1번  모든 사용자의 이름과 이메일을 조회하시오
        System.out.println("1번 모든 사용자의 이름과 이메일을 조회하시오." );
        String selectBoardSQL = "SELECT username from Users;";
        ResultSet result = jdbc.executeQuery(selectBoardSQL);

        while(result.next()){
            System.out.println(
                    " 이름 : " + result.getString("username"));
        }
        result.close();
        // 2번
        System.out.println("2번 모든 게시글의 메시지와 생성 시간을 조회하시오." );
        selectBoardSQL = "SELECT message, created_at from Posts";
        result = jdbc.executeQuery(selectBoardSQL);

        while(result.next()){
            System.out.println(
                    " 메시지 : " + result.getString("message")+
                    " 생성시간 : "  + result.getDate("created_at"));
        }
        result.close();
        // 3번
        System.out.println("3번 게시글이 1개 이상 있는 모든 사용자의 이름을 조회하시오.");
        selectBoardSQL = "select u.username from Users u left join posts p \n" +
                "on u.id = p.user_id group by u.username\n" +
                "having count(p.id) >= 1;";
        result = jdbc.executeQuery(selectBoardSQL);
        while(result.next()){
            System.out.println(
                    " 이름 : " + result.getString("u.username"));
        }
        result.close();
        // 4번
        System.out.println("4번 최근 24시간 내에 생성된 게시글의 메시지를 조회하시오." );
        selectBoardSQL ="select message, created_at  from posts p " +
                "where created_at > DATE_ADD(curdate(), interval-1 DAY);";
        result = jdbc.executeQuery(selectBoardSQL);

        while(result.next()){
            System.out.println(
                    " 메시지 : " + result.getString("message") +
                    " 생성시간 : "  + result.getDate("created_at"));
        }
        result.close();
        // 5번
        System.out.println("5번 모든 사용자의 이름과 그들의 게시글 수를 조회하시오." );
        selectBoardSQL = "select u.username, count(p.id) as cnt from users u \n" +
                "left join posts p on u.id = p.user_id\n" +
                "group by u.username;";
        result = jdbc.executeQuery(selectBoardSQL);
        while(result.next()){
            System.out.println(
                    " 이름 : " + result.getString("username") +
                    " 게시글 수 : " + result.getInt("cnt"));
        }
        result.close();
        //6번
        System.out.println( "6번 'like'가 포함된 게시글 메시지를 조회하시오."  );
        selectBoardSQL = "select message from posts p where message like '%like%';";
        result = jdbc.executeQuery(selectBoardSQL);
        while(result.next()){
            System.out.println(
                    " 메시지 : " + result.getString("message"));
        }
        result.close();
        //7번
        System.out.println("7번 게시글 중 좋아요가 5개 이상인 게시글의 메시지를 조회하시오." );
        selectBoardSQL = "select p.message from posts p join post_likes pl \n" +
                "on p.id = pl.post_id \n" +
                "group by p.id\n" +
                "having count(pl.id) >= 5;";
        result = jdbc.executeQuery(selectBoardSQL);
        while(result.next()){
            System.out.println(
                    " 메시지 : " + result.getString("message"));
        }
        result.close();
        // 8번
        System.out.println("8번 각 게시글에 대한 총 댓글 수를 조회하시오." );
        selectBoardSQL = "select p.id, count(pc.id) as cnt from posts p \n" +
                "join post_comments pc on p.id =pc.post_id\n" +
                "group by p.id;";
        result = jdbc.executeQuery(selectBoardSQL);
        while(result.next()){
            System.out.println(
                    " 게시글 id : " + result.getInt("p.id") +
                    " 총 댓글 수 : " + result.getInt("cnt"));
        }
        result.close();
        // 9번
        System.out.println("9번 가장 많은 팔로워를 가진 사용자의 이름을 조회하시오." );
        selectBoardSQL = "select ff.follower_id , count(*) from follow_follower ff \n" +
                "group by ff.follower_id\n" +
                "having count(*) = (select Max(cnt) from (\n" +
                "select ff.follower_id , count(*) as cnt  from follow_follower ff \n" +
                "group by ff.follower_id) as sq);";
        result = jdbc.executeQuery(selectBoardSQL);
        while(result.next()){
            System.out.println(
                    " user이름  : " + result.getString("u.username") +
                    " 팔로워 수 : " + result.getInt("mx"));
        }
        result.close();
        // 10번
        System.out.println("10번 가장 최근에 가입한 5명의 사용자 이름과 가입 시간을 조회하시오." );
        selectBoardSQL = "select u.username, u.created_at  from users u \n" +
                "order by u.created_at desc\n" +
                "limit 5;";
        result = jdbc.executeQuery(selectBoardSQL);
        while(result.next()){
            System.out.println(
                    " user이름  : " + result.getString("u.username") +
                    " 가입 시간 : " + result.getDate("u.created_at"));
        }
        result.close();
        // 11번
        System.out.println("11번 특정 사용자의 이름을 변경하시오. " +
                "예를 들어, 사용자 'user1'의 이름을 'newUser1'로 변경하시오." );
        String updateUsersSQL = "UPDATE Users SET username = 'newUser1' " +
                "WHERE username = 'user1'";
        int updateRows = jdbc.executeUpdate(updateUsersSQL);
        System.out.println(updateRows + " 명의 유저이름이 변경되었습니다.");
        result.close();
        // 12번
        System.out.println("12번 문제 " +
                "게시글의 좋아요 수를 증가시키는 쿼리를 작성하시오. " +
                "예를 들어, 게시글 ID가 3인 게시글의 좋아요 수를 5 증가시키시오." );
        String updatePostTotalLikesSQl  = "update posts set total_likes = ifnull(total_likes,0) + 5\n" +
                "where id = 3;";
        updateRows = jdbc.executeUpdate(updatePostTotalLikesSQl);
        System.out.println(updateRows + "개의 게시글 ID가 3인 게시글의 좋아요 수를 5 증가시켰어요");
        result.close();
        // 13번
        System.out.println("13번 문제 " +
                "특정 사용자의 모든 게시글의 메시지에 추가 텍스트를 삽입하시오." +
                " 예를 들어, 사용자 ID가 2인 사용자의 모든 게시글에 \" - updated\"를 메시지 끝에 추가하시오.");
        String updateMessageSQL = "update posts set message = concat(message, '-updated')\n" +
                "where id = 2;\n";
        updateRows = jdbc.executeUpdate(updateMessageSQL);
        System.out.println(updateRows + "개의 게시글을 변경시켰어요");
        result.close();
        // 14번
        System.out.println("14번 문제 " +
                "사용자의 이메일 주소를 업데이트하시오. " +
                "예를 들어, 사용자 ID가 4인 사용자의 이메일을 'newemail@example.com'으로 변경하시오.");
        String updateEmailSQl = "update users set email = 'newemail@example.com'" +
                "where id = 4;";
        updateRows = jdbc.executeUpdate(updateEmailSQl);
        System.out.println(updateRows + "개의 유저 이메일을 변경시켰어요");
        result.close();
        // 15번
        System.out.println("15번 문제 " +
                "특정 게시글의 생성 날짜를 현재 날짜와 시간으로 업데이트하시오. " +
                "예를 들어, 게시글 ID가 1인 게시글의 생성 날짜를 현재 날짜와 시간으로 업데이트하시오.");
        String updateCreatedAtSQL = "update posts set created_at = current_timestamp()\n" +
                "where id = 4;";
        updateRows = jdbc.executeUpdate(updateCreatedAtSQL);
        System.out.println(updateRows + "개의 게시글 생성 시간을 변경시켰어요");
        result.close();
        // 16번
        System.out.println("16번 문제 " +
                "특정 사용자의 모든 게시글을 삭제하시오. " +
                "예를 들어, 사용자 ID가 5인 사용자의 모든 게시글을 삭제하시오.");
        String deletePostSQL = "Delete from posts where user_id = 5;";
        int deleteRows = jdbc.executeUpdate(deletePostSQL);
        System.out.println(deleteRows + "개의 게시글을 지웠어요");
        result.close();
        // 17번
        System.out.println("17번 문제 " +
                "특정 사용자의 모든 게시글을 삭제하시오. " +
                "예를 들어, 사용자 ID가 5인 사용자의 모든 게시글을 삭제하시오.");
        String deleteUserSQL = "Delete from Users where id = 5;";
        deleteRows =jdbc.executeUpdate(deleteUserSQL);
        System.out.println(deleteRows + "개의 유저를 지웠어요");

        result.close();
        jdbc.close();

    }
}

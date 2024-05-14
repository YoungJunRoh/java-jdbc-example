package Practice;

import java.sql.SQLException;

public class InsertIntoExample {
    public static void main(String[] args) throws SQLException {
        Jdbc jdbc = new Jdbc("jdbc:mysql://127.0.0.1:3306/Instagram",
                "root", "asdasd1");
        jdbc.connect(jdbc);

        String sqlInsertUsers = "INSERT INTO users (username, password) VALUES " +
                "('user1', 'password1'), ('user2', 'password2'), ('user3', 'password3'), " +
                "('user4', 'password4'), ('user5', 'password5'), ('user6', 'password6'), " +
                "('user7', 'password7'), ('user8', 'password8'), ('user9', 'password9'), " +
                "('user10', 'password10')";

        int boardRows = jdbc.executeUpdate(sqlInsertUsers);
        System.out.println(boardRows + "개의 글이 Users 테이블에 생성되었습니다");

        String sqlInsertPosts = "INSERT INTO posts (image, message, user_id) VALUES " +
                "(NULL, 'Hello World', 1), (NULL, 'Learning Java', 2), " +
                "(NULL, 'At the beach', 3), (NULL, 'Eating pizza', 4), " +
                "(NULL, 'Watching a movie', 5), (NULL, 'At a concert', 6), " +
                "(NULL, 'Playing games', 7), (NULL, 'Reading a book', 8), " +
                "(NULL, 'Visiting museums', 9), (NULL, 'Going hiking', 10)";

        boardRows = jdbc.executeUpdate(sqlInsertPosts);
        System.out.println(boardRows + "개의 글이 Posts 테이블에 생성되었습니다.");


        String sqlInsertComments = "INSERT INTO Post_Comments (comment, user_id, post_id) VALUES " +
                "('Nice post!', 1, 1), ('Love this!', 2, 1), ('So cool!', 3, 2), " +
                "('Wow!', 4, 3), ('Great pic!', 5, 3), ('Can\\'t agree more!', 6, 4), " +
                "('LOL', 7, 5), ('That\\'s awesome!', 8, 6), ('Yummy!', 9, 7), ('Interesting!', 10, 8)";

        boardRows = jdbc.executeUpdate(sqlInsertComments);
        System.out.println(boardRows + "개의 글이 Post_Comments 테이블에 생성되었습니다.");


        String sqlInsertLikes = "INSERT INTO Post_Likes (user_id, post_id) VALUES " +
                "(1, 1), (2, 1), (3, 1), (4, 2), (5, 2), " +
                "(6, 3), (7, 3), (8, 4), (9, 5), (10, 5)";

        boardRows = jdbc.executeUpdate(sqlInsertLikes);
        System.out.println(boardRows + "개의 글이 Post_Likes 테이블에 생성되었습니다.");


        String sqlInsertFollows = "INSERT INTO Follow_Follower (follower_id, user_id) VALUES " +
                "(1, 2), (1, 3), (2, 3), (2, 4), (3, 4), " +
                "(3, 5), (4, 5), (4, 6), (5, 6), (5, 7)";
        boardRows = jdbc.executeUpdate(sqlInsertFollows);
        System.out.println(boardRows + "개의 글이 Follow_Follower 테이블에 생성되었습니다.");

        String sqlInsertHashtags = "INSERT INTO Hashtags (name) VALUES " +
                "('summer'), ('java'), ('beach'), ('pizza'), ('movie'), " +
                "('concert'), ('games'), ('book'), ('museum'), ('hiking')";
        boardRows = jdbc.executeUpdate(sqlInsertHashtags);
        System.out.println(boardRows + "개의 글이 Hashtags 테이블에 생성되었습니다.");


        String sqlInsertPostsHashtags = "INSERT INTO Post_Hashtags (hashtag_id, post_id) VALUES " +
                "(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), " +
                "(6, 6), (7, 7), (8, 8), (9, 9), (10, 10)";

        boardRows = jdbc.executeUpdate(sqlInsertPostsHashtags);
        System.out.println(boardRows + "개의 글이 Post_Hashtags 테이블에 생성되었습니다.");

        jdbc.close();

    }
}

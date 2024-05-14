package Practice;

import java.sql.SQLException;

public class CreateTables {
    public static void main(String[] args) throws SQLException {
        Jdbc jdbc = new Jdbc("jdbc:mysql://127.0.0.1:3306/Instagram",
                "root", "asdasd1");
        jdbc.connect(jdbc);

        String SQL = "create table IF NOT EXISTS Users (\n" +
                "\tid INT primary key auto_increment,\n" +
                "\tusername VARCHAR(50) not null,\n" +
                "\tpassword VARCHAR(50) not null,\n" +
                "\temail VARCHAR(50) not null default 'example@example.com',\n" +
                "\tcreated_at DATETIME default CURRENT_TIMESTAMP\n" +
                ")\n";
        jdbc.executeUpdate(SQL);
        System.out.println("Users 테이블을 생성했습니다.");

        SQL = "create table IF NOT EXISTS Follow_Follower (" +
                "id INT primary key auto_increment," +
                "follower_id INT," +
                "user_id INT," +
                "foreign key (follower_id) references Users (id) on delete cascade," +
                "foreign key (user_id) references Users (id) on delete cascade" +
                ")";
        jdbc.executeUpdate(SQL);
        System.out.println("Follow_Follower 테이블을 생성했습니다.");

        SQL = " create table IF NOT EXISTS Posts(" +
                " id INT primary key auto_increment,\n" +
                " \timage blob,\n" +
                " \tmessage text,\n" +
                " \tcreated_at DATETIME default CURRENT_TIMESTAMP,\n" +
                " \ttotal_likes INT,\n" +
                " \ttotal_comments INT,\n" +
                " \tuser_id INT,\n" +
                " \tforeign key (user_id) references Users(id) on delete cascade\n" +
                ")";
        jdbc.executeUpdate(SQL);
        System.out.println("Posts 테이블을 생성했습니다.");

        SQL = "create table IF NOT EXISTS Post_Comments (\n" +
                "\tid INT primary key auto_increment,\n" +
                "\tcomment VARCHAR(255),\n" +
                "\tcreated_at DATETIME default CURRENT_TIMESTAMP,\n" +
                "\tuser_id INT,\n" +
                "\tpost_id INT,\n" +
                "\tforeign key (user_id) references Users(id) on delete cascade,\n" +
                "\tforeign key (post_id) references Posts(id) on delete cascade\n" +
                ")";
        jdbc.executeUpdate(SQL);
        System.out.println("Post_Comments 테이블을 생성했습니다.");

        SQL = "create table IF NOT EXISTS Post_Likes(\n" +
                "\tid INT primary key auto_increment,\n" +
                "\tcreated_at DATETIME default CURRENT_TIMESTAMP,\n" +
                "\tuser_id INT,\n" +
                "\tpost_id INT,\n" +
                "\tforeign key (user_id) references Users(id) on delete cascade,\n" +
                "\tforeign key (post_id) references Posts(id) on delete cascade\n" +
                ")";
        jdbc.executeUpdate(SQL);
        System.out.println("Post_Likes 테이블을 생성했습니다.");

        SQL = "create table IF NOT EXISTS Hashtags(\n" +
                "\tid INT primary key auto_increment,\n" +
                "\tname char(50) not null\n" +
                ")";
        jdbc.executeUpdate(SQL);
        System.out.println("Hashtags 테이블을 생성했습니다.");

        SQL = "create table IF NOT EXISTS Post_Hashtags(\n" +
                "\tid INT primary key auto_increment,\n" +
                "\thashtag_id INT,\n" +
                "\tpost_id INT,\n" +
                "\tforeign key (hashtag_id) references Hashtags(id) on delete cascade,\n" +
                "\tforeign key (post_id) references Posts(id) on delete cascade\n" +
                ")";
        jdbc.executeUpdate(SQL);
        System.out.println("Post_Hashtags 테이블을 생성했습니다.");

        jdbc.close();
    }
}

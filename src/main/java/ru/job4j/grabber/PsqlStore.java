package ru.job4j.grabber;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private Connection cnn;

    public PsqlStore(Properties cfg) throws Exception {
        try {
            Class.forName(cfg.getProperty("driver_class"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        cnn = DriverManager.getConnection(cfg.getProperty("url"),
                cfg.getProperty("login"),
                cfg.getProperty("password"));
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement =
                     cnn.prepareStatement(
                             "insert into post(post_name, link, description, created_date)"
                             + "values (?, ?, ?, ?) on conflict "
                                     + "on constraint post_link_key do nothing")) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getLink());
            statement.setString(3, post.getDescription());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement =
                     cnn.prepareStatement("select * from post")) {
           try (ResultSet resultSet = statement.executeQuery()) {
               while (resultSet.next()) {
                   posts.add(new Post(
                           resultSet.getInt("id"),
                           resultSet.getString("post_name"),
                           resultSet.getString("link"),
                           resultSet.getString("description"),
                           resultSet.getTimestamp("created_date").toLocalDateTime()
                           ));
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(int id) {
        Post post = new Post(0, "", "", "", LocalDateTime.now());
        try (PreparedStatement statement =
                     cnn.prepareStatement("select * from post where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    post.setId(resultSet.getInt("id"));
                    post.setTitle(resultSet.getString("post_name"));
                    post.setLink(resultSet.getString("link"));
                    post.setDescription(resultSet.getString("description"));
                    post.setCreated(resultSet.getTimestamp("created_date").toLocalDateTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static Properties read() {
        Properties rsl = new Properties();
        try (InputStream in = new FileInputStream("db/parse.properties")) {
            rsl.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        Properties pr = read();
        try {
            PsqlStore psqlStore = new PsqlStore(pr);
            DateTimeParser dtp = new HabrCareerDateTimeParser();
            Parse parse = new HabrCareerParse(dtp);
            List<Post> posts = parse.list(pr.getProperty("parse_link"));
            for (Post post : posts) {
                psqlStore.save(post);
            }
            System.out.println(psqlStore.getAll());
            System.out.println(psqlStore.findById(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

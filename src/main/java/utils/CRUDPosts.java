package utils;

import entities.Posts;
import entities.SomePosts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDPosts {
    private static final String GET_ALL_POSTS = "SELECT * from posts";
    private static final String GET_SOME_USERS_POST = "SELECT users.name, posts.content_of_post from posts INNER JOIN users ON users.id = posts.user_id WHERE posts.user_id = ?";
    private static final String INSERT_POST = "INSERT INTO posts (content_of_post, user_id) values (?, ?)";
    private static final String UPDATE_POST = "UPDATE posts SET content_of_post = ? WHERE id = ?";
    private static final String DELETE_ALL_POSTS = "DELETE from posts";
    private static final String DELETE_SOME_POST = "DELETE from posts where id = ?";

    public static List<Posts> getAllPosts() {
        List<Posts> posts = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_POSTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Posts not found");
                return null;
            } else {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String content = resultSet.getString("content_of_post");
                    int userId = resultSet.getInt("user_id");

                    posts.add(new Posts(id, content, userId));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static List<SomePosts> getSomeUsersPosts(int userId) {
        List<SomePosts> somePosts = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SOME_USERS_POST)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Posts not found");
                return null;
            } else {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String content = resultSet.getString("content_of_post");

                    somePosts.add(new SomePosts(name, content));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return somePosts;
    }

    public static void addPost(String content, int userId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POST)) {
            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, userId);
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("User ID " + userId + " not found");
            } else {
                System.out.println("User ID " + userId + " post added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePost(String content, int postId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POST)) {
            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, postId);
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("Post ID " + postId + " not found");
            } else {
                System.out.println("Post ID " + postId + " updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllPosts() {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_POSTS)) {
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("Posts not found");
            } else {
                System.out.println("All posts deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSomePosts(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SOME_POST)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("Post not found");
            } else {
                System.out.println("Posts ID " + id + " deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

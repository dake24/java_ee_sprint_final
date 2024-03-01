package kz.example.practiceJavaEE.repository;

import kz.example.practiceJavaEE.model.Comment;
import kz.example.practiceJavaEE.model.News;
import kz.example.practiceJavaEE.model.NewsCategory;
import kz.example.practiceJavaEE.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static Connection con;
    private static PreparedStatement statement;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createUser(Users user) {
        String query = "INSERT INTO users (email, password, full_name, role_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Users getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Users user = new Users();
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFullName(resultSet.getString("full_name"));
                    user.setRoleId(resultSet.getInt("role_id"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Users getUserByEmail(String email) throws SQLException {
        statement = con.prepareStatement("SELECT * from users where email=?");
        statement.setString(1, email);

        ResultSet rs = statement.executeQuery();

        Users u = null;
        if (rs.next()) {
            u = new Users(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("fullname"),
                    rs.getInt("role_id")
            );
        }

        return u;
    }

    public static void updateUser(Users user) {
        String query = "UPDATE users SET email = ?, password = ?, full_name = ?, role_id = ? WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleId());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createNews(News news) {
        String query = "INSERT INTO news (post_date, category_id, title, content) VALUES (now(), ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, news.getCategory().getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this more appropriately in your application
        }
    }

    public static List<News> getAllNews() {
        String query = "SELECT * FROM news";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            List<News> newsList = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    News news = new News();
                    news.setId(resultSet.getInt("id"));
                    news.setPostDate(resultSet.getTimestamp("post_date"));
                    news.setTitle(resultSet.getString("title"));
                    news.setContent(resultSet.getString("content"));

                    NewsCategory category = getNewsCategoryById(resultSet.getInt("category_id"));
                    news.setCategory(category);
                    newsList.add(news);
                }
                return newsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static News getNewsById(int id) {
        String query = "SELECT * FROM news WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    News news = new News();
                    news.setId(resultSet.getInt("id"));
                    news.setPostDate(resultSet.getTimestamp("post_date"));
                    news.setTitle(resultSet.getString("title"));
                    news.setContent(resultSet.getString("content"));

                    NewsCategory category = getNewsCategoryById(resultSet.getInt("category_id"));
                    news.setCategory(category);
                    return news;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update news
    public static boolean updateNews(News news) {
        String query = "UPDATE news SET post_date = now(), category_id = ?, title = ?, content = ? WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, news.getCategory().getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.setInt(4, news.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteNews(int id) {
        String query = "DELETE FROM news WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static NewsCategory getNewsCategoryById(int id) {
        String query = "SELECT * FROM news_categories WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    NewsCategory category = new NewsCategory();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    return category;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<NewsCategory> getAllNewsCategory() {
        String query = "SELECT * FROM news_categories";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<NewsCategory> newsCategories = new ArrayList<>();
                while (resultSet.next()) {
                    NewsCategory category = new NewsCategory();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    newsCategories.add(category);
                }
                return newsCategories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createNewsCategory(NewsCategory category) {
        String query = "INSERT INTO news_categories (name) VALUES (?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, category.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createComment(Comment comment) {
        String query = "INSERT INTO comments (comment, post_date, user_id, news_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, comment.getComment());
            statement.setTimestamp(2, comment.getPostDate());
            statement.setInt(3, comment.getUserId());
            statement.setInt(4, comment.getNewsId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this more appropriately in your application
        }
    }

    public static List<Comment> getCommentsById(int newsId) {
        String query = "SELECT * FROM comments WHERE news_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, newsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Comment> comments = new ArrayList<>();
                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setId(resultSet.getInt("id"));
                    comment.setComment(resultSet.getString("comment"));
                    comment.setPostDate(resultSet.getTimestamp("post_date"));
                    comment.setUserId(resultSet.getInt("user_id"));
                    comment.setNewsId(resultSet.getInt("news_id"));
                    comments.add(comment);
                }
                return comments;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateComment(Comment comment) {
        String query = "UPDATE comments SET comment = ?, post_date = now(), user_id = ?, news_id = ? WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, comment.getComment());
            statement.setInt(2, comment.getUserId());
            statement.setInt(3, comment.getNewsId());
            statement.setInt(4, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteComment(int id) {
        String query = "DELETE FROM comments WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this more appropriately in your application
        }
    }
}

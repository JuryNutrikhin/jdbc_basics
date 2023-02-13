package org.example.dao;

import org.example.model.Author;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private String url;
    private String user;
    private String password;

    private GenreDao genreDao;

    public AuthorDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.genreDao = new GenreDaoImpl(url, user, password);
    }

    @Override
    public Author getById(int id) throws SQLException {
        try (Connection cnn = DriverManager.getConnection(url, user, password)) {
            String query =
                    "SELECT * FROM author a\n" +
                            "INNER JOIN book b on a.author_id = b.author_id\n" +
                            "WHERE a.author_id = ?";
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet data = statement.getResultSet();
            data.next();

            Author author = new Author();
            author.setName(data.getString("name_author"));
            author.setId(data.getInt("author_id"));

            List<Book> books = new ArrayList<>();

            do {
                Book book = new Book();
                book.setAuthor(author);
                book.setId(data.getInt("book_id"));
                book.setAmount(data.getInt("amount"));
                book.setPrice(data.getDouble("price"));
                book.setTitle(data.getString("title"));
                book.setGenre(genreDao.getById(data.getInt("genre_id")));
                books.add(book);
            } while (data.next());

            author.setBooks(books);
            return author;
        }
    }

    @Override//вывести на экран авторов и их книги
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
     try(Connection cnn =  DriverManager.getConnection(url,user,password)){
         String query =
                 "select * from book b " +
                         " inner join  author a on a.author_id = b.author_id " +
                         " inner join  genre g on g.genre_id = b.genre_id  ";

         Statement statement = cnn.createStatement();
         ResultSet resultSet = statement.executeQuery(query);

      //  --------------------------------------
//        try (Connection cnn = DriverManager.getConnection(url, user, password)) {
//            String query =
//                    "SELECT * FROM author a\n" +
//                            "INNER JOIN book b on a.author_id = b.author_id\n" +
//                            "WHERE a.author_id = ?";
//            PreparedStatement statement = cnn.prepareStatement(query);
//                statement.setInt(1, 1);
////            statement.execute();
//            statement.execute();
//            ResultSet resultSet = statement.getResultSet();
//            resultSet.next();
//------------------------------------------------
          //  Author author = new Author();

            while (resultSet.next()) {
                 Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("name_author"));
               Book book = new Book();
               book.setAuthor(author);
               book.setId(resultSet.getInt("book_id"));
               book.setTitle(resultSet.getString("title"));
               book.setGenre(genreDao.getById(resultSet.getInt("genre_id")));
               book.setPrice(resultSet.getInt("price"));
               book.setAmount(resultSet.getInt("amount"));

               books.add(book);
            }
        }
        return books;
    }

    @Override
    public void save(Author author) { // сохранить автора в базе

    }

    @Override
    public void delete(int id) {//удалить по id

    }
}

package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.AuthorDaoImpl;
import org.example.dao.AuthorDaoImpl2;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

public class Main {
    public static void main(String[] args) throws SQLException {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Не удалось загрузить драйвер");
//        }
        String url = "jdbc:postgresql://localhost:5432/home_work";
        String user = "postgres";
        String password = "1111";

        AuthorDao dao = new AuthorDaoImpl(url, user, password);







        Author author = dao.getById(2);
        System.out.println("Author: " + author.getName());
        System.out.println("Books: ");
        author.getBooks().forEach(book -> System.out.println(book.getTitle()));


        //2. Реализуйте методы getAll в AuthorDao
        List<Book> booksList = new ArrayList<>();
        booksList = dao.getAll();

        booksList.forEach(b -> System.out.println(
                "   id книги : " + b.getId() +
                        "\n      Книга : " + b.getTitle() +
                        "\n  id Автора : " + b.getAuthor().getId() +
                        "\n      Автор : " + b.getAuthor().getName() +
                        "\n    id жанр : " + b.getGenre().getId() +
                        "\n       Жанр : " + b.getGenre().getName() +
                        "\n       Цена : " + b.getPrice() +
                        "\n Количество : " + b.getAmount() + "\n"));

        //3. Реализуйте методы   delete в AuthorDao


        //3. Реализуйте методы  saveAuthorDao
//        List<Book> books1 = new ArrayList<>();
//
//        Author author1 = new Author();
//        author1.setName("bob");
//
//        Genre genre = new Genre();
//        genre.setId(1);
//        genre.setName("Роман");
//
//        Book b = new Book();
//        b.setAmount(10);
//        b.setPrice(333);
//        b.setTitle("война и мир");
//        b.setAuthor(author1);
//        b.setGenre(genre);
//        books1.add(b);
//        author1.setBooks(books1);
       // Реализуйте методы save в AuthorDao
        System.out.println("Введите автора для сохранения ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        Author author1 = new Author();
        author1.setName(name);

        dao.save(author1);
      //  Реализуйте методы delete в AuthorDao
        System.out.println(" введите Id  нужного автора  : ");
        int id = scanner.nextInt();

        dao.delete(id);

    }
}

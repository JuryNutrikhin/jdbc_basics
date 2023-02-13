package org.example.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class Author {
    private int id;
    private String name;
    private List<Book> books;

}

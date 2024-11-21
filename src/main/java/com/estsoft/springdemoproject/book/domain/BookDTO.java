package com.estsoft.springdemoproject.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    private String id;
    private String name;
    private String author;

    public BookDTO(Book book) {
        id = book.id;
        name = book.name;
        author = book.author;
    }
}

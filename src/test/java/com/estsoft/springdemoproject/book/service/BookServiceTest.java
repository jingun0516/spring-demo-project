package com.estsoft.springdemoproject.book.service;

import com.estsoft.springdemoproject.book.domain.Book;
import com.estsoft.springdemoproject.book.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Test
    public void testFindAll() {
        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book("1", "name1", "author1"));
        books.add(new Book("2", "name2", "author2"));
        books.add(new Book("3", "name3", "author3"));
        when(bookService.findAll()).thenReturn(books);


        // when
        List<Book> savedBooks = bookService.findAll();


        // then
        assertThat(savedBooks).hasSize(3);
    }

    @Test
    public void testFindBy() {
        Book book1 = new Book("1", "name1", "author1");
        when(bookRepository.findById("1")).thenReturn(Optional.of(book1));

        Book returnedBook = bookService.findBy("1");

        assertThat(returnedBook).isNotNull();
        assertThat(returnedBook.getName()).isEqualTo("name1");
        assertThat(returnedBook.getAuthor()).isEqualTo("author1");

        verify(bookRepository, times(1)).findById("1");
    }

    @Test
    public void testSaveOne() {
        Book book1 = new Book("1", "name1", "author1");
        when(bookRepository.save(book1)).thenReturn(book1);

        Book savedBook = bookService.saveOne(book1);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getName()).isEqualTo("name1");
        assertThat(savedBook.getAuthor()).isEqualTo("author1");

        verify(bookRepository, times(1)).save(book1);
    }

    public Book saveOne(Book book) {
        return bookRepository.save(book);
    }

}
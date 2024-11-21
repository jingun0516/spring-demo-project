package com.estsoft.springdemoproject.book.controller;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.estsoft.springdemoproject.book.domain.Book;
import com.estsoft.springdemoproject.book.repository.BookRepository;
import com.estsoft.springdemoproject.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@SpringBootTest
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService; // Mock 객체로 변경

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        // MockMvc 초기화
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    void showAll() throws Exception {
        // given
        List<Book> books = List.of(
                new Book("1", "Effective Java", "Joshua Bloch"),
                new Book("2", "Clean Code", "Robert C. Martin")
        );
        when(bookService.findAll()).thenReturn(books); // Mock 설정

        // when
        ResultActions resultActions = mockMvc.perform(get("/books"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("bookManagement"))
                .andExpect(model().attribute("bookList", hasSize(2))) // Mock 데이터를 기준으로 검증
                .andExpect(model().attribute("bookList", hasItem(hasProperty("name", is("Effective Java")))));
    }

    @Test
    void testShowOne() throws Exception {
        // given
        String id = "1";
        Book book = new Book(id, "Effective Java", "Joshua Bloch");
        when(bookService.findBy(id)).thenReturn(book); // Mock 설정

        // when
        ResultActions resultActions = mockMvc.perform(get("/books/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("bookDetail"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("book", hasProperty("name", is("Effective Java"))))
                .andExpect(model().attribute("book", hasProperty("author", is("Joshua Bloch"))));
    }

    @Test
    void addBook() throws Exception {
        // given
        String id = "3";
        String author = "Jin Geon";
        String name = "New Book";

        // when
        ResultActions resultActions = mockMvc.perform(post("/books")
                .param("id", id)
                .param("author", author)
                .param("name", name)
        );

        // then
        resultActions.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }
}

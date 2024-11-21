package com.estsoft.springdemoproject.blog.controller;

import com.estsoft.springdemoproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springdemoproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springdemoproject.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "블로그 저장/수정/삭제/조회용 API", description = "API 설명을 이곳에 작성하면 됩니다")
public class BlogController {
    private final BlogService service;

    public BlogController(BlogService service) {
        this.service = service;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/articles")
    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request) {
        log.info("request: {}, {}", request.getTitle(), request.getContent());
        Article article = service.saveArticle(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ArticleResponse(article));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청에 성공했습니다.", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면에서 보여주는 전체 목록")
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> findAll() {
        // List<Article> -> List<ArticleResponse> 변환해서 응답으로 보내기
        List<ArticleResponse> list = service.findAll().stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok(list);
    }

    @Parameter(name = "id", description = "블로그 글 ID", example = "45")
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
        Article article = service.findBy(id);   // Exception (5xx server error) -> 4xx Status Code

        // Article -> ArticleResponse
        return ResponseEntity.ok(new ArticleResponse(article));
    }

    @Parameter(name = "id", description = "블로그 글 ID", example = "1")
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(@PathVariable Long id,
                                                      @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = service.update(id, request);
        return ResponseEntity.ok(new ArticleResponse(updatedArticle));
    }
}

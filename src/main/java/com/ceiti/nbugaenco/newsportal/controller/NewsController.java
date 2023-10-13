package com.ceiti.nbugaenco.newsportal.controller;

import com.ceiti.nbugaenco.newsportal.model.Comment;
import com.ceiti.nbugaenco.newsportal.model.News;
import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.model.request.CreateCommentRequest;
import com.ceiti.nbugaenco.newsportal.model.request.CreateNewsRequest;
import com.ceiti.nbugaenco.newsportal.model.request.PaginationRequest;
import com.ceiti.nbugaenco.newsportal.model.response.SearchCommentsResponse;
import com.ceiti.nbugaenco.newsportal.model.response.SearchNewsResponse;
import com.ceiti.nbugaenco.newsportal.service.CommentService;
import com.ceiti.nbugaenco.newsportal.service.NewsService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsController {

  private final CommentService commentService;
  private final NewsService newsService;

  @GetMapping
  public ResponseEntity<SearchNewsResponse> getNews(
      @Valid @RequestBody PaginationRequest paginationRequest) {
    return ResponseEntity.ok(newsService.getNews(paginationRequest));
  }

  @GetMapping("{id}")
  public ResponseEntity<News> getNewsById(@PathVariable UUID id) {
    return ResponseEntity.ok(newsService.getNewsById(id));
  }

  @PostMapping
  public ResponseEntity<News> createNews(@AuthenticationPrincipal User user,
      @Valid @RequestBody CreateNewsRequest createNewsRequest) {
    return ResponseEntity.ok(newsService.createNews(user, createNewsRequest));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteNews(@PathVariable UUID id) {
    return ResponseEntity.ok(newsService.deleteById(id));
  }

  @GetMapping("{id}/comments")
  public ResponseEntity<SearchCommentsResponse> getComments(@PathVariable UUID id,
      @Valid @RequestBody PaginationRequest paginationRequest) {
    return ResponseEntity.ok(commentService.getComments(paginationRequest, id));
  }

  @PostMapping("{id}/comments")
  public ResponseEntity<Comment> createComment(@PathVariable UUID id,
      @AuthenticationPrincipal User user,
      @Valid @RequestBody CreateCommentRequest createCommentRequest) {
    return ResponseEntity.ok(commentService.createComment(user, createCommentRequest, id));
  }

  @DeleteMapping("{newsId}/comments/{commentId}")
  public ResponseEntity<Void> deleteComment(@PathVariable UUID newsId,
      @PathVariable UUID commentId) {
    return ResponseEntity.ok(commentService.deleteComment(newsId, commentId));
  }

}

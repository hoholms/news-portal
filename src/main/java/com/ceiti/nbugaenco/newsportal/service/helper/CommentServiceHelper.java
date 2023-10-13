package com.ceiti.nbugaenco.newsportal.service.helper;

import com.ceiti.nbugaenco.newsportal.model.Comment;
import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.model.request.CreateCommentRequest;
import com.ceiti.nbugaenco.newsportal.model.response.SearchCommentsResponse;
import com.ceiti.nbugaenco.newsportal.service.NewsService;
import com.ceiti.nbugaenco.newsportal.util.CommonUtils;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentServiceHelper {

  private final NewsService newsService;
  private final CommonServiceHelper commonServiceHelper;

  public SearchCommentsResponse toSearchCommentsResponse(Page<Comment> commentPage) {
    return new SearchCommentsResponse()
        .withPagination(commonServiceHelper.toPaginationResponse(commentPage))
        .withComments(asComments(commentPage));
  }

  public Comment toComment(User user, CreateCommentRequest createCommentRequest, UUID newsId) {
    return new Comment()
        .withId(UUID.randomUUID())
        .withContent(createCommentRequest.getContent())
        .withNews(newsService.getNewsById(newsId))
        .withUser(user)
        .withCreateTimestamp(CommonUtils.toInstant())
        .withUpdateTimestamp(CommonUtils.toInstant());
  }

  private List<Comment> asComments(Page<Comment> commentPage) {
    return Optional.ofNullable(commentPage)
        .map(Slice::getContent)
        .orElseGet(Collections::emptyList);
  }
}

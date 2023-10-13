package com.ceiti.nbugaenco.newsportal.service;

import com.ceiti.nbugaenco.newsportal.model.Comment;
import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.model.request.CreateCommentRequest;
import com.ceiti.nbugaenco.newsportal.model.request.PaginationRequest;
import com.ceiti.nbugaenco.newsportal.model.response.SearchCommentsResponse;
import com.ceiti.nbugaenco.newsportal.repository.CommentRepository;
import com.ceiti.nbugaenco.newsportal.service.helper.CommentServiceHelper;
import com.ceiti.nbugaenco.newsportal.service.helper.CommonServiceHelper;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommonServiceHelper commonServiceHelper;
  private final CommentServiceHelper commentServiceHelper;
  private final CommentRepository commentRepository;

  public SearchCommentsResponse getComments(PaginationRequest paginationRequest, UUID newsId) {
    Page<Comment> commentPage = commentRepository.findAllByNews_Id(newsId,
        commonServiceHelper.toPageable(paginationRequest));
    return commentServiceHelper.toSearchCommentsResponse(commentPage);
  }

  public Comment createComment(User user, CreateCommentRequest createCommentRequest, UUID newsId) {
    return commentRepository.save(
        commentServiceHelper.toComment(user, createCommentRequest, newsId));
  }

  public void deleteAllByNews(UUID id) {
    commentRepository.deleteAllByNews_Id(id);
  }

  @Transactional
  public Void deleteComment(UUID newsId, UUID commentId) {
    commentRepository.deleteByIdAndNews_Id(commentId, newsId);
    return Void.TYPE.cast(null);
  }
}

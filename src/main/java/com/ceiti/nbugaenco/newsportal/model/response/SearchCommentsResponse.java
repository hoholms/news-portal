package com.ceiti.nbugaenco.newsportal.model.response;

import com.ceiti.nbugaenco.newsportal.model.Comment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class SearchCommentsResponse {

  private List<Comment> comments;
  private PaginationResponse pagination;
}

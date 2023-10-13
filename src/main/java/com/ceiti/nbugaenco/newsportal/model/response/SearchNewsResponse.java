package com.ceiti.nbugaenco.newsportal.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class SearchNewsResponse {

  private List<ShortNews> shortNews;
  private PaginationResponse pagination;
}

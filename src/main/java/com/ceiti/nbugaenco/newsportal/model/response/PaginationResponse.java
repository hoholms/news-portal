package com.ceiti.nbugaenco.newsportal.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse {

  private Integer pageNumber;
  private Integer pageSize;
  private Integer totalPages;
  private Long totalElements;
}

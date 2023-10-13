package com.ceiti.nbugaenco.newsportal.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaginationRequest {

  @NotNull
  private Integer pageNumber;
  @NotNull
  private Integer pageSize;
  private String sort;
}

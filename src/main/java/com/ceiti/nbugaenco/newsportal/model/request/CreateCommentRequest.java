package com.ceiti.nbugaenco.newsportal.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCommentRequest {

  @NotBlank(message = "Provide comment's content")
  private String content;
}

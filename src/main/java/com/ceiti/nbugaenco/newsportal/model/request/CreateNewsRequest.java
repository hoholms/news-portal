package com.ceiti.nbugaenco.newsportal.model.request;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Data;

@Data
public class CreateNewsRequest implements Serializable {

  @NotBlank(message = "Provide news title")
  private String title;
  @NotBlank(message = "Provide news content")
  private String content;
}

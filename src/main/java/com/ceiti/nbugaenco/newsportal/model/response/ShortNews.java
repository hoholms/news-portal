package com.ceiti.nbugaenco.newsportal.model.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class ShortNews {

  private UUID id;
  private String title;
  private String shortContent;
  private UUID author;
}

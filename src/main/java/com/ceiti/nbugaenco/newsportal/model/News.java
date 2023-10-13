package com.ceiti.nbugaenco.newsportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news", indexes = {
    @Index(name = "idx_news_author_id", columnList = "author_id")
})
public class News {

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "content", length = Integer.MAX_VALUE)
  private String content;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author_id")
  private User author;

  @Column(name = "create_timestamp")
  private Instant createTimestamp;

  @Column(name = "update_timestamp")
  private Instant updateTimestamp;

}
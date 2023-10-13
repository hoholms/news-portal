package com.ceiti.nbugaenco.newsportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "comment", indexes = {
    @Index(name = "idx_comment_user_id", columnList = "user_id"),
    @Index(name = "idx_comment_news_id", columnList = "news_id")
})
public class Comment {

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "content", nullable = false, length = 256)
  private String content;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "news_id")
  private News news;

  @Column(name = "create_timestamp")
  private Instant createTimestamp;

  @Column(name = "update_timestamp")
  private Instant updateTimestamp;

}
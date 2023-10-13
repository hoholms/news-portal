package com.ceiti.nbugaenco.newsportal.repository;

import com.ceiti.nbugaenco.newsportal.model.Comment;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

  Page<Comment> findAllByNews_Id(UUID id, Pageable pageable);

  void deleteAllByNews_Id(UUID id);

  void deleteByIdAndNews_Id(UUID id, UUID newsId);

}
package com.ceiti.nbugaenco.newsportal.repository;

import com.ceiti.nbugaenco.newsportal.model.News;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {

}
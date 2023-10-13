package com.ceiti.nbugaenco.newsportal.service;

import com.ceiti.nbugaenco.newsportal.exception.NewsPortalException;
import com.ceiti.nbugaenco.newsportal.model.News;
import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.model.request.CreateNewsRequest;
import com.ceiti.nbugaenco.newsportal.model.request.PaginationRequest;
import com.ceiti.nbugaenco.newsportal.model.response.SearchNewsResponse;
import com.ceiti.nbugaenco.newsportal.repository.NewsRepository;
import com.ceiti.nbugaenco.newsportal.service.helper.CommonServiceHelper;
import com.ceiti.nbugaenco.newsportal.service.helper.NewsServiceHelper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {

  private final CommonServiceHelper commonServiceHelper;
  private final NewsRepository newsRepository;
  private final NewsServiceHelper newsServiceHelper;

  public SearchNewsResponse getNews(PaginationRequest paginationRequest) {
    Page<News> newsPage = newsRepository.findAll(commonServiceHelper.toPageable(paginationRequest));
    return newsServiceHelper.toSearchNewsResponse(newsPage);
  }

  public News getNewsById(UUID id) {
    return newsRepository.findById(id).orElseThrow(
        () -> new NewsPortalException("News with id: " + id + " not found", HttpStatus.NOT_FOUND));
  }

  public News createNews(User user, CreateNewsRequest createNewsRequest) {
    return newsRepository.save(newsServiceHelper.toNews(user, createNewsRequest));
  }

  public Void deleteById(UUID id) {
    newsRepository.deleteById(id);
    return Void.TYPE.cast(null);
  }
}

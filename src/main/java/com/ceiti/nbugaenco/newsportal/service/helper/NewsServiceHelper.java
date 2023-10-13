package com.ceiti.nbugaenco.newsportal.service.helper;

import com.ceiti.nbugaenco.newsportal.model.News;
import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.model.request.CreateNewsRequest;
import com.ceiti.nbugaenco.newsportal.model.response.SearchNewsResponse;
import com.ceiti.nbugaenco.newsportal.model.response.ShortNews;
import com.ceiti.nbugaenco.newsportal.util.CommonUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsServiceHelper {

  public static final String ELLIPSIS = "...";

  private final CommonServiceHelper commonServiceHelper;

  private static UUID asAuthorId(News news) {
    return Optional.ofNullable(news)
        .map(News::getAuthor)
        .map(User::getId)
        .orElse(null);
  }

  public SearchNewsResponse toSearchNewsResponse(Page<News> newsPage) {
    return new SearchNewsResponse()
        .withPagination(commonServiceHelper.toPaginationResponse(newsPage))
        .withShortNews(toShortNewsList(newsPage));
  }

  public News toNews(User user, CreateNewsRequest createNewsRequest) {
    return new News()
        .withId(UUID.randomUUID())
        .withTitle(createNewsRequest.getTitle())
        .withContent(createNewsRequest.getContent())
        .withAuthor(user)
        .withCreateTimestamp(CommonUtils.toInstant())
        .withUpdateTimestamp(CommonUtils.toInstant());
  }

  private List<ShortNews> toShortNewsList(Page<News> newsPage) {
    return newsPage.stream()
        .map(this::toShortNews)
        .toList();
  }

  private ShortNews toShortNews(News news) {
    return new ShortNews()
        .withId(news.getId())
        .withAuthor(asAuthorId(news))
        .withTitle(news.getTitle())
        .withShortContent(asShortContent(news.getContent()));
  }

  private String asShortContent(String content) {
    return Optional.ofNullable(content)
        .filter(content1 -> content1.length() > 50)
        .map(content1 -> content1.substring(0, 46))
        .map(String::trim)
        .map(StringBuilder::new)
        .map(stringBuilder -> stringBuilder.append(ELLIPSIS))
        .map(StringBuilder::toString)
        .orElse(content);
  }
}

package com.ceiti.nbugaenco.newsportal.service.helper;

import com.ceiti.nbugaenco.newsportal.model.request.PaginationRequest;
import com.ceiti.nbugaenco.newsportal.model.response.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonServiceHelper {

  public Pageable toPageable(PaginationRequest paginationRequest) {
    if (paginationRequest.getSort() != null) {
      return PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize(),
          Sort.Direction.ASC, paginationRequest.getSort());
    } else {
      return PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
    }
  }

  public PaginationResponse toPaginationResponse(Page<?> newsPage) {
    return new PaginationResponse()
        .withPageNumber(newsPage.getNumber())
        .withPageSize(newsPage.getSize())
        .withTotalPages(newsPage.getTotalPages())
        .withTotalElements(newsPage.getTotalElements());
  }

}

package com.ceiti.nbugaenco.newsportal.model;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
  USER,
  ADMIN;

  @Override
  public String getAuthority() {
    return name();
  }
}

package com.ceiti.nbugaenco.newsportal.service;

import com.ceiti.nbugaenco.newsportal.exception.NewsPortalException;
import com.ceiti.nbugaenco.newsportal.model.Authority;
import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() ->
            new UsernameNotFoundException(
                String.format("User with username \"%s\" not found", username)));
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void updateUser(
      UUID userId,
      String username,
      Boolean enabled,
      Map<String, String> form
  ) {
    User user = findUserById(userId);

    user.setUsername(username);
    user.setEnabled(enabled);

    Set<String> authorities = Arrays.stream(Authority.values())
        .map(Authority::name)
        .collect(Collectors.toSet());

    user.getAuthority().clear();

    for (String key : form.keySet()) {
      if (authorities.contains(key)) {
        user.getAuthority().add(Authority.valueOf(key));
      }
    }

    userRepository.save(user);
  }

  public void deleteById(UUID userID) {
    findUserById(userID);
    userRepository.deleteById(userID);
  }

  public List<User> findAllUsers() {
    return userRepository.findAll(Sort.by(Sort.Order.by("id")));
  }

  public User findUserById(UUID id) {
    return userRepository.findById(id).orElseThrow(
        () -> new NewsPortalException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));
  }

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }
}
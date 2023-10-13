package com.ceiti.nbugaenco.newsportal.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@UtilityClass
public class CommonUtils {

  public static Map<String, String> getErrors(BindingResult bindingResult) {
    Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
        fieldError -> fieldError.getField() + "Error",
        FieldError::getDefaultMessage,
        (fieldError1, fieldError2) -> fieldError1 + ". " + fieldError2
    );

    return bindingResult.getFieldErrors().stream().collect(collector);
  }

  public static Instant toInstant() {
    return Instant.now().truncatedTo(ChronoUnit.SECONDS);
  }

}

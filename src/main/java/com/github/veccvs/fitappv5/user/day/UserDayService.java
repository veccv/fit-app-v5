package com.github.veccvs.fitappv5.user.day;

import com.github.veccvs.fitappv5.auth.AuthenticationService;
import com.github.veccvs.fitappv5.exception.ResourceFoundException;
import com.github.veccvs.fitappv5.exception.ResourceNotFoundException;
import com.github.veccvs.fitappv5.product.CustomProduct;
import com.github.veccvs.fitappv5.user.UserRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDayService {
  private final UserDayRepository userDayRepository;
  private final UserRepository userRepository;
  private final AuthenticationService authenticationService;

  public UserDay createUserDay(LocalDate date) {
    if (!userDayRepository.findAllByDateEquals(date).isEmpty())
      throw new ResourceFoundException("Day has been already created!");

    var user = userRepository.findByEmail(authenticationService.getUserLogin()).orElseThrow();
    return userDayRepository.save(UserDay.builder().date(date).userId(user.getId()).build());
  }

  public UserDay addProductToDay(Integer userDayId, DayTime dayTime, CustomProduct customProduct) {
    var userDay =
        userDayRepository
            .findById(userDayId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "User day id [%s] not found".formatted(userDayId)));

    var user = userRepository.findById(userDay.getUserId()).orElseThrow();
    if (!user.getEmail().equals(authenticationService.getUserLogin()))
      throw new ResourceNotFoundException(
          "User day [%s] does not exist for user [%s]"
              .formatted(userDayId, authenticationService.getUserLogin()));

    if (dayTime == DayTime.BREAKFAST) userDay.getBreakfastProducts().add(customProduct);

    if (dayTime == DayTime.LUNCH) userDay.getLunchProducts().add(customProduct);

    return userDayRepository.save(userDay);
  }

  public UserDay getDayById(Integer id) {
    var userDay =
        userDayRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("User day id [%s] not found".formatted(id)));

    var user = userRepository.findById(userDay.getUserId()).orElseThrow();
    if (!user.getEmail().equals(authenticationService.getUserLogin()))
      throw new ResourceNotFoundException(
          "User day [%s] does not exist for user [%s]"
              .formatted(id, authenticationService.getUserLogin()));

    return userDay;
  }

  public UserDay getDayByDate(LocalDate date) {
    var user = userRepository.findByEmail(authenticationService.getUserLogin()).orElseThrow();
    return userDayRepository.findAllByDateEquals(date).stream()
        .filter(day -> day.getUserId().equals(user.getId()))
        .findFirst()
        .orElseThrow(
            () -> new ResourceNotFoundException("User day date [%s] not found".formatted(date)));
  }

  public UserDay addProductsToDay(
      Integer userDayId, DayTime dayTime, List<CustomProduct> customProducts) {
    customProducts.forEach(customProduct -> addProductToDay(userDayId, dayTime, customProduct));

    return getDayById(userDayId);
  }

  public UserDay updateDay(UserDay userDay) {
    return userDayRepository.save(userDay);
  }
}

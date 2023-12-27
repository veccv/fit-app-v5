package com.github.veccvs.fitappv5.user.day;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDayRepository extends JpaRepository<UserDay, Integer> {
  List<UserDay> findAllByDateEquals(LocalDate date);
}

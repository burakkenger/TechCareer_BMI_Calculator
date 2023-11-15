package com.burakkenger.spring_bmi_calculator.data.repository;

import com.burakkenger.spring_bmi_calculator.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}

package com.burakkenger.spring_bmi_calculator.data.repository;

import com.burakkenger.spring_bmi_calculator.data.entity.Bmi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBmiRepository extends JpaRepository<Bmi, Long> {
    Bmi findByUser_Id(Long id);
    Iterable<Bmi> findAllByUser_Id(Long id);
}

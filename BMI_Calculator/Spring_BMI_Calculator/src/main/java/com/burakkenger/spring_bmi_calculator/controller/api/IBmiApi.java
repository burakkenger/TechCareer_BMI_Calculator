package com.burakkenger.spring_bmi_calculator.controller.api;

import com.burakkenger.spring_bmi_calculator.business.dto.BmiDto;
import org.springframework.http.ResponseEntity;

public interface IBmiApi {
    public void Add(BmiDto bmiDto);
    public ResponseEntity<?> GetByUserID();
    public ResponseEntity<?> GetByAllUserID();
    public void DeleteID(Long key);
}

package com.burakkenger.spring_bmi_calculator.controller.api;

import com.burakkenger.spring_bmi_calculator.business.dto.LoginRequest;
import com.burakkenger.spring_bmi_calculator.business.dto.LoginResponse;
import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import com.burakkenger.spring_bmi_calculator.data.entity.User;
import org.springframework.http.ResponseEntity;

public interface IAuthApi {

    public ResponseEntity<?> register(UserDto userDto);
    public LoginResponse login(LoginRequest req);
}

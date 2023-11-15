package com.burakkenger.spring_bmi_calculator.controller.api.concretes;

import com.burakkenger.spring_bmi_calculator.business.abstracts.IAuthService;
import com.burakkenger.spring_bmi_calculator.business.dto.LoginRequest;
import com.burakkenger.spring_bmi_calculator.business.dto.LoginResponse;
import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import com.burakkenger.spring_bmi_calculator.controller.api.IAuthApi;
import com.burakkenger.spring_bmi_calculator.data.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/api/")
@CrossOrigin
public class AuthApi implements IAuthApi {

    private final IAuthService authService;

    @PostMapping(value = "/register")
    @Override
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(authService.register(userDto));
    }

    @PostMapping("/login")
    @Override
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}

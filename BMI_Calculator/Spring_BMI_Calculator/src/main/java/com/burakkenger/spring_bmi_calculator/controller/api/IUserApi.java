package com.burakkenger.spring_bmi_calculator.controller.api;

import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface IUserApi {

    public ResponseEntity<UserDto> SearchByID(Long id);
    public void AddData(UserDto userDto);
    public void Update(String username);
}

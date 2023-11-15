package com.burakkenger.spring_bmi_calculator.business.abstracts;

import com.burakkenger.spring_bmi_calculator.business.dto.LoginRequest;
import com.burakkenger.spring_bmi_calculator.business.dto.LoginResponse;
import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import com.burakkenger.spring_bmi_calculator.data.entity.User;

public interface IAuthService {
    public User DtoToEntity(UserDto d);
    public LoginResponse login(LoginRequest req);
    public UserDto register(UserDto d);
}

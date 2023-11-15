package com.burakkenger.spring_bmi_calculator.business.abstracts;

import org.springframework.security.core.userdetails.User;

public interface IJwtService {
    public String generateJwtToken(User principal);
}

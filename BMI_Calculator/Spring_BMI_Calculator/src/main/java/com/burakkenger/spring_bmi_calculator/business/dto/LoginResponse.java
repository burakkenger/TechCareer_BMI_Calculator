package com.burakkenger.spring_bmi_calculator.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse implements Serializable {

    public static final Long serialVersionUID = 1L;

    private String username;
    private String authToken;
}

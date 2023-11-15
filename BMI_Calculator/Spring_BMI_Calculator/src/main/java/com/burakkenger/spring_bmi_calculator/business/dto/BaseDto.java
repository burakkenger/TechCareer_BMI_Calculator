package com.burakkenger.spring_bmi_calculator.business.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Getter
@Setter
abstract public class BaseDto implements Serializable {

    public static final Long serialVersionUID = 1L;

    protected Long id;
}

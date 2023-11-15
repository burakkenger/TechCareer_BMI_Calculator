package com.burakkenger.spring_bmi_calculator.business.dto;

import com.burakkenger.spring_bmi_calculator.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BmiDto extends BaseDto implements Serializable {

    public static final Long serialVersionUID = 1L; //serileştirme

    private Double height;

    private Double weight;

    private Double result;

    private String resultName;

    private Date createDate;

    private User user;
}

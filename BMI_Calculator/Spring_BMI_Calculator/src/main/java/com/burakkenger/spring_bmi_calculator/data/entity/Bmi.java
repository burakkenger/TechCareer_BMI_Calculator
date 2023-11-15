package com.burakkenger.spring_bmi_calculator.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Bmi extends BaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L; //serileştirme

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "result")
    private Double result;

    @Column(name = "result_name")
    private String resultName;

    @Column(name = "create_date")
    private Date createDate = new Date(System.currentTimeMillis());

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
}

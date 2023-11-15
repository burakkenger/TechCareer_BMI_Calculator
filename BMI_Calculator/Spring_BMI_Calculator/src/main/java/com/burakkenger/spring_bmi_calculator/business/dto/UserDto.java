package com.burakkenger.spring_bmi_calculator.business.dto;

import com.burakkenger.spring_bmi_calculator.data.entity.Bmi;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.List;

@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends BaseDto implements Serializable {

    public static final Long serialVersionUID = 1L;

    @NotNull(message = "Kullanıcı adı boş geçilemez")
    private String username;

    @NotNull(message = "Şifre boş geçilemez")
    private String password;

    @NotNull(message = "İsim boş geçilemez")
    private String name;

    @NotNull(message = "Soyisim boş geçilemez")
    private String surname;

    @NotNull(message = "Mail Adresi boş geçilemez")
    private String email;

    private List<Bmi> bmis;
}

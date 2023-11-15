package com.burakkenger.spring_bmi_calculator.storage;

import com.burakkenger.spring_bmi_calculator.data.repository.IUserRepository;
import lombok.*;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Component
public class ActiveUserInfo {
    private String username;
    private GrantedAuthority grantedAuthority;
}

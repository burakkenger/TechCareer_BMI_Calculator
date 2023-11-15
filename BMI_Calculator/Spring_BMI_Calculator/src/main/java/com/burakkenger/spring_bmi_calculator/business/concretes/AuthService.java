package com.burakkenger.spring_bmi_calculator.business.concretes;

import com.burakkenger.spring_bmi_calculator.business.abstracts.IAuthService;
import com.burakkenger.spring_bmi_calculator.business.dto.LoginRequest;
import com.burakkenger.spring_bmi_calculator.business.dto.LoginResponse;
import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import com.burakkenger.spring_bmi_calculator.data.entity.User;
import com.burakkenger.spring_bmi_calculator.data.repository.IUserRepository;
import com.burakkenger.spring_bmi_calculator.storage.ActiveUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ActiveUserInfo activeUserInfo;

    @Override
    public User DtoToEntity(UserDto userDto) {
        return new ModelMapper().map(userDto, User.class);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String token = jwtService.generateJwtToken(principal);

        activeUserInfo.setUsername(principal.getUsername());
        activeUserInfo.setGrantedAuthority(principal.getAuthorities().iterator().next());

        return new LoginResponse(
                request.getUsername(), token
        );
    }

    @Override
    public UserDto register(UserDto userDto) {
        if (userDto != null) {
            userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
            userRepository.save(DtoToEntity(userDto));
            return userDto;
        } else throw new RuntimeException("Kullanıcı bilgileri gelmedi");
    }
}

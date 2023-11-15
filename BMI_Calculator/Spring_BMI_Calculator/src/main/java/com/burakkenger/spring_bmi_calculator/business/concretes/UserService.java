package com.burakkenger.spring_bmi_calculator.business.concretes;

import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import com.burakkenger.spring_bmi_calculator.business.abstracts.IUserService;
import com.burakkenger.spring_bmi_calculator.data.entity.User;
import com.burakkenger.spring_bmi_calculator.data.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService implements IUserService<UserDto, User>, UserDetailsService {

    private final IUserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User DtoToEntity(UserDto userDto) {
        return new ModelMapper().map(userDto, User.class);
    }

    @Override
    public UserDto EntityToDto(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }

    @Override
    public UserDto findByID(Long key) {
        Optional<User> userData = userRepository.findById(key);
        if (userData.isPresent()) {
            return EntityToDto(userData.get());
        }
        throw new RuntimeException("Veri alınamadı");
    }

    @Override
    public void Insert(UserDto userDto) {
        if (userDto != null) {
            User user = DtoToEntity(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else throw new RuntimeException("user bilgileri boş");
    }

    @Override
    public UserDto findByUsername(String username) {
        Optional<User> userData = userRepository.findByUsername(username);
        User findingUser;
        if (userData.isPresent()) {
            findingUser = userData.get();
            return EntityToDto(findingUser);
        } else throw new RuntimeException("Kullanıcı bulunamadı");
    }

    @Override
    public String Update(String value, User user) {
        User userUpdate = user;
        userUpdate.setUsername(value);
        userRepository.save(userUpdate);
        return "işlem tamamlandı";
    }


    @Override //giriş yapmak isteyen kullanıcının kullanıcı adını alıp sistemde var mı diye kontrol eden ve varsa bilgilerini alıp rollerini veren fonksiyon
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userData = userRepository.findByUsername(username);
        if (userData.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                username, userData.get().getPassword(), List.of(new SimpleGrantedAuthority("USER"))
            );
        } else throw new UsernameNotFoundException("Kullanıcı bulunamadı");
    }
}

package com.burakkenger.spring_bmi_calculator.controller.api.concretes;

import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import com.burakkenger.spring_bmi_calculator.business.abstracts.IUserService;
import com.burakkenger.spring_bmi_calculator.controller.api.IUserApi;
import com.burakkenger.spring_bmi_calculator.data.entity.User;
import com.burakkenger.spring_bmi_calculator.storage.ActiveUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Log4j2
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user/api/")
public class UserApi implements IUserApi {

    private final IUserService userService;
    private final ActiveUserInfo activeUserInfo;
    private User getUserData() {
        String currentUsername = activeUserInfo.getUsername();
        UserDto userDto = (UserDto) userService.findByUsername(currentUsername);
        User user = (User) userService.DtoToEntity(userDto);
        return user;
    }

    @Override
    @GetMapping(value = "/search_id/{id}")
    public ResponseEntity<UserDto> SearchByID(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body((UserDto) userService.findByID(id));
    }

    @Override
    public void AddData(@Valid @RequestBody UserDto userDto) {
        userService.Insert(userDto);
    }

    @Override
    @PostMapping("/update")
    public void Update(@PathVariable(name = "username") String username) {
        User user = getUserData();
        userService.Update(username,user);
    }
}

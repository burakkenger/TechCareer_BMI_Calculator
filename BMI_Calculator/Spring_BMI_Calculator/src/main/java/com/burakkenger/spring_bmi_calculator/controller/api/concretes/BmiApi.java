package com.burakkenger.spring_bmi_calculator.controller.api.concretes;

import com.burakkenger.spring_bmi_calculator.business.abstracts.IBmiService;
import com.burakkenger.spring_bmi_calculator.business.abstracts.IUserService;
import com.burakkenger.spring_bmi_calculator.business.dto.BmiDto;
import com.burakkenger.spring_bmi_calculator.business.dto.UserDto;
import com.burakkenger.spring_bmi_calculator.controller.api.IBmiApi;
import com.burakkenger.spring_bmi_calculator.data.entity.User;
import com.burakkenger.spring_bmi_calculator.storage.ActiveUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/bmi/api/")
@CrossOrigin
public class BmiApi implements IBmiApi {

    private final IBmiService bmiService;
    private final IUserService userService;
    private final ActiveUserInfo activeUserInfo;

    private User getUserData() {
        String currentUsername = activeUserInfo.getUsername();
        UserDto userDto = (UserDto) userService.findByUsername(currentUsername);
        User user = (User) userService.DtoToEntity(userDto);
        return user;
    }

    @Override
    @PostMapping("/add")
    public void Add(@RequestBody BmiDto bmiDto) {
        bmiDto.setUser(getUserData());
        bmiService.Insert(bmiDto);
    }

    @Override
    @GetMapping("/get_by_user")
    public ResponseEntity<?> GetByUserID() {
        return ResponseEntity.ok().body(bmiService.getByUserID(getUserData().getId()));
    }

    @Override
    @GetMapping("/get_by_all_user")
    public ResponseEntity<?> GetByAllUserID() {
        return ResponseEntity.ok().body(bmiService.getByAllUserID(getUserData().getId()));
    }

    @Override
    @DeleteMapping("/delete/{key}")
    public void DeleteID(@PathVariable(name = "key") Long key) {
        bmiService.DeleteByID(key);
    }
}

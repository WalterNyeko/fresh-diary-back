package com.fresh.freshdiary.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.freshdiary.exception.ValidationException;
import com.fresh.freshdiary.model.UserInfo;
import com.fresh.freshdiary.repository.UserInfoRepository;
import com.fresh.freshdiary.service.UserServiceImpl;

@RestController
@CrossOrigin
public class UserInfoController {


    final
    private UserInfoRepository userInfoRepository;
    
    @Autowired
    private UserServiceImpl userService;

//    private HashData hashData = new HashData();

    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }


    @PostMapping("/user")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (userInfoRepository.existsByUsername(username)){

            throw new ValidationException("Username already existed");

        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
        String fullname = body.get("fullname");
        userInfoRepository.save(new UserInfo(username, encodedPassword, fullname));
        return true;
    }
    
    @GetMapping("/users")
    public Iterable<UserInfo> getAllUsers(){
    	return userService.listAllUsers();
    }

}

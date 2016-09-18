package com.viifly.wba.admin.controller;

import com.viifly.wba.admin.User;
import com.viifly.wba.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public String addUser(@RequestBody MultiValueMap<String, String> form) {
        User user = new User();
        user.setLoginId(form.getFirst("loginid"));
        user.setNickName(form.getFirst("nickname"));
        user.setEmailAddress(form.getFirst("email"));
        user.setStatus("NEW");
        Date n = new Date();
        user.setCreatedDate(n);
        user.setStatusChangedDate(n);
        user.setModifiedDate(n);

        userService.addUser(user);

        return "ok";
    }
}

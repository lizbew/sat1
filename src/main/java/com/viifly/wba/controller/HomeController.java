package com.viifly.wba.controller;

import com.viifly.wba.admin.User;
import com.viifly.wba.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/home")
    public String homeView(Model model) {
        List<User> custList = userService.findAllUsers();
        model.addAttribute("custList", custList);
        return "home";
    }

    @RequestMapping(value = "/about")
    public String aboutView(Model model) {
        return "about";
    }
}

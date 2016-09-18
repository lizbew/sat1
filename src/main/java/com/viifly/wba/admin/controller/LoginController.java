package com.viifly.wba.admin.controller;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    //@ResponseBody
    @GetMapping
    public String loginForm(Model model) {
        UsernamePasswordAuthenticationFilter a;
        model.addAttribute("user", "test");
        return "login/login_form";

        //return new ModelAndView("admin/login_form", "user", "test");
    }
}

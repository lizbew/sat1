package com.viifly.wba.mongo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/mongo")
public class MongoController {

    @GetMapping
    public String homeView() {
        return "mongo/home.ftl";
    }
}

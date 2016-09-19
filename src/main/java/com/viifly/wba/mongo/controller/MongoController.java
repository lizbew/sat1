package com.viifly.wba.mongo.controller;


import com.viifly.wba.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/mongo")
public class MongoController {

    private MongoService mongoService;

    @Autowired
    public void setMongoService(MongoService mongoService) {
        this.mongoService = mongoService;
    }


    @GetMapping
    public String homeView(Model model) {
        List<String> dbList = mongoService.listDatabases();
        model.addAttribute("dbList", dbList);

        return "mongo/home";
    }
}

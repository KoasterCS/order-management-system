package com.order.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Index {

    @GetMapping()
    Map index(){
        Map map = new HashMap();
        map.put("title", "Home Page Orders Management System.");
        map.put("date", new Date());
        return  map;
    }
}

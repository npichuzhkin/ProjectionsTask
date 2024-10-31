package com.npichuzhkin.ProjectionsTask.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @GetMapping("/home")
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>("Hello from public resource", HttpStatus.OK);
    }
}

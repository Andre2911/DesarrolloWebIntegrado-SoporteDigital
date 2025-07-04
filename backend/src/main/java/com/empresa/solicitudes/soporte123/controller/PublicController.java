package com.empresa.solicitudes.soporte123.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "OK";
    }
}

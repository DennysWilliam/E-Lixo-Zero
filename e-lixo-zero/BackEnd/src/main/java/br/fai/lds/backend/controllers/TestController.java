package br.fai.lds.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String hello() {
        return "Backend E-Lixo Zero funcionando!";
    }
}
package com.mkacunha.processadorcep.application.controller.login;

import com.mkacunha.processadorcep.domain.login.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public ResponseEntity login(@RequestParam("code") String code) {
        try {
            return ResponseEntity.ok(loginService.login(code));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


}

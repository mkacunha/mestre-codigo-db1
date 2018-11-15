package com.mkacunha.processadorcep.application.controller.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class TesteController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping
    public String teste(@RequestParam("user") String username, @RequestParam("message") String message) {
        System.out.println("/topic/" + username);
        messagingTemplate.convertAndSend("/topic/" + username, message);
        return message;
    }


}

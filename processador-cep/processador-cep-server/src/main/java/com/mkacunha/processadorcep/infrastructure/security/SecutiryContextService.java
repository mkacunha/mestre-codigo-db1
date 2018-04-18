package com.mkacunha.processadorcep.infrastructure.security;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecutiryContextService {

    private final Map<String, User> loggedUsers;

    public SecutiryContextService(Map<String, User> loggedUsers) {
        this.loggedUsers = loggedUsers;
    }

    public void register(UserToken token, User user) {
        this.loggedUsers.putIfAbsent(token.getAccess_token(), user);
    }

    public boolean hasToken(String token) {
        return loggedUsers.containsKey(token);
    }
}

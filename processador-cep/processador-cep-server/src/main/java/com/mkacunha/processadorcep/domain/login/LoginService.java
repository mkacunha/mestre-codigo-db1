package com.mkacunha.processadorcep.domain.login;

import com.mkacunha.processadorcep.infrastructure.security.SecutiryContextService;
import com.mkacunha.processadorcep.infrastructure.security.User;
import com.mkacunha.processadorcep.infrastructure.security.UserToken;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class LoginService {

    public static final String URL_GRAPH_FACEBOOK = "https://graph.facebook.com/v2.10/oauth/access_token";

    public static final String URL_ME_FACEBOOK = "https://graph.facebook.com/v2.10/me?fields=id,name";

    public static final String CLIENT_ID = "791476447724146";

    public static final String CLIENT_SECRET = "69eb4a714a05535a76f45c68674399fe";

    public static final String REDIRECT_URI = "http://localhost:8080/login";

    public static final String MSG_ERROR_LOGIN = "Não foi possível executar login com Facebook. Entre em contato com o administrador do sistema.";

    private final RestTemplate restTemplate;

    private final SecutiryContextService secutiryContextService;

    public LoginService(RestTemplate restTemplate, SecutiryContextService secutiryContextService) {
        this.restTemplate = restTemplate;
        this.secutiryContextService = secutiryContextService;
    }

    public UserToken login(String code) {
        try {
            UserToken token = this.getToken(code);
            User user = this.getUser(token);
            secutiryContextService.register(token, user);
            return token;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException(MSG_ERROR_LOGIN);
        }
    }

    private UserToken getToken(String code) {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("client_id", CLIENT_ID);
        parts.add("client_secret", CLIENT_SECRET);
        parts.add("redirect_uri", REDIRECT_URI);
        parts.add("code", code);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts);
        ResponseEntity<UserToken> response =
                restTemplate.exchange(URL_GRAPH_FACEBOOK, HttpMethod.POST, requestEntity, UserToken.class);
        return response.getBody();
    }

    private User getUser(UserToken loginToken) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_ME_FACEBOOK).queryParam("access_token", loginToken.getAccess_token());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        URI url = builder.build().encode().toUri();
        HttpEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, entity, User.class);
        return response.getBody();
    }

    public User me(String token) {
        return secutiryContextService.getUser(token);
    }
}

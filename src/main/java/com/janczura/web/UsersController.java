package com.janczura.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UsersController {


    @GetMapping(value = "/tokens")
    public String[] getTokens(
            @RegisteredOAuth2AuthorizedClient("eloprindelse") OAuth2AuthorizedClient authorizedClient
    ) {
        return new String[]{authorizedClient.getAccessToken().getTokenValue(),
                authorizedClient.getRefreshToken().getTokenValue()};

    }
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
}
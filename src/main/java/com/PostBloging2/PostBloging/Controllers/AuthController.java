
package com.PostBloging2.PostBloging.Controllers;

import com.PostBloging2.PostBloging.DTO.AuthDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class AuthController {

    @Value("${client-id}")
    private String clientId;

    @Value("${resource-url}")
    private String resourceUrl;

    @Value("${grant-type}")
    private String grantType;

    @PostMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var body = "client_id=" + clientId +
                "&username=" + authDTO.login() +
                "&password=" + authDTO.password() +
                "&grant_type=" + grantType;

        var requestEntity = new HttpEntity<>(body, headers);
        var restTemplate = new RestTemplate();

        var responce = restTemplate.exchange(
                resourceUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        if (responce.getStatusCode().value() == 200) {
            return responce.getBody();
        }
        return null;

    }
}


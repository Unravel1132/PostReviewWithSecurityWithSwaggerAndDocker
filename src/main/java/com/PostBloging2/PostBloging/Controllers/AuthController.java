
package com.PostBloging2.PostBloging.Controllers;

import com.PostBloging2.PostBloging.DTO.AuthDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {

    @Value("${client_id")
    private String clientId;

    @Value("${resource-url")
    private String resourceUrl;

    @Value("${grant-type")
    private String grantType;

    @GetMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var body = "client_id=" + clientId +
                "&username" + authDTO.login() +
                "&password" + authDTO.password()
                + "&grant_type=" + grantType;


        var requestEntity = new HttpEntity<AuthDTO>(authDTO, headers);
        var restTemplate = new RestTemplate();
        var response = restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        if (response.getStatusCodeValue() == 200) {
            return response.getBody();
        }
        return null;
    }
}


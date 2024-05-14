package org.example.productservice.commons;

import org.example.productservice.models.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO validateToken(String tokenValue){
        //Call the userService to validate the token.

        ResponseEntity<UserDTO> responseEntity =
                restTemplate.getForEntity(
                        "http://localhost:4141/users/validate/" + tokenValue,
                        UserDTO.class
                );

        if (responseEntity.getBody() == null) {
            //token is invalid.
            //Throw some exception here.
            return null;
        }

        return responseEntity.getBody();
    }


}

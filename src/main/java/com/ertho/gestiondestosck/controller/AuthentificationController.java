package com.ertho.gestiondestosck.controller;

import com.ertho.gestiondestosck.dto.auth.AuthentificationRequest;
import com.ertho.gestiondestosck.dto.auth.AuthentificationResponse;
import com.ertho.gestiondestosck.services.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ertho.gestiondestosck.utils.Constants.AUTHENTIFICATION_ENDPOINT;


@RestController
@RequestMapping(AUTHENTIFICATION_ENDPOINT)
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private  ApplicationUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthentificationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        return ResponseEntity.ok(AuthentificationResponse.builder().accessToken("ertho_access_token").build());
    }

}

package com.ertho.gestiondestosck.controller;

import com.ertho.gestiondestosck.dto.auth.AuthenticationRequest;
import com.ertho.gestiondestosck.dto.auth.AuthenticationResponse;
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

import static com.ertho.gestiondestosck.utils.Constants.AUTHENTICATION_ENDPOINT;


@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  ApplicationUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken("erthoo_access_token").build());
    }

}

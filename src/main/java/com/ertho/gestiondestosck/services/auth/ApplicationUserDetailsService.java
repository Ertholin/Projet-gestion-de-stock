package com.ertho.gestiondestosck.services.auth;

import com.ertho.gestiondestosck.exception.EntityNotFoundException;
import com.ertho.gestiondestosck.exception.ErrorCodes;
import com.ertho.gestiondestosck.model.Utilisateur;
import com.ertho.gestiondestosck.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email).orElseThrow(()->
            new EntityNotFoundException("Aucun utilisateur avec cet email", ErrorCodes.UTILISATEUR_NOT_FOUND)
        );

        return new User(utilisateur.getEmail(), utilisateur.getMoteDePasse(), Collections.emptyList());
    }
}

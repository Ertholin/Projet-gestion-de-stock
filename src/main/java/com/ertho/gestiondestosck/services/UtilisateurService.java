package com.ertho.gestiondestosck.services;

import com.ertho.gestiondestosck.dto.ChangerMotDePasseUtilisateurDto;
import com.ertho.gestiondestosck.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    UtilisateurDto findByEmail(String email);

    List<UtilisateurDto> findAll();

    void delete(Integer id);

    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);
}

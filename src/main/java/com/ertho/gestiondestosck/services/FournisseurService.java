package com.ertho.gestiondestosck.services;

import com.ertho.gestiondestosck.dto.FournisseurDto;
import com.ertho.gestiondestosck.model.Fournisseur;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    void delete(Integer id);

}

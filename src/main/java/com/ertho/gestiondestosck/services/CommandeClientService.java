package com.ertho.gestiondestosck.services;

import com.ertho.gestiondestosck.dto.CommandeClientDto;
import com.ertho.gestiondestosck.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {


    CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto updateEtatCommande(Integer id, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommandee(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Integer idCommande, Integer idClient);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void delete(Integer id);
}

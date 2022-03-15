package com.ertho.gestiondestosck.controller;

import com.ertho.gestiondestosck.controller.api.CommandeClientApi;
import com.ertho.gestiondestosck.dto.CommandeClientDto;
import com.ertho.gestiondestosck.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientControllery implements CommandeClientApi {

    @Autowired
    private CommandeClientService commandeClientService;

    public CommandeClientControllery(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        return commandeClientService.save(dto);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return commandeClientService.findById(id);
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return commandeClientService.findByCode(code);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);
    }
}

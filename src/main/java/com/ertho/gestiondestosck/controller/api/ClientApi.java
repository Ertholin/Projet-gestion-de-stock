package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/clients")
public interface ClientApi {


    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un client", description = "Cette méthode permet d'enregisrer ou modifier un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet Client créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet Client n'est pas valide")
    })
    ClientDto save(ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un client par ID", description = "Cette méthode permet de rechercher un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le client a été trouvé dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucun client n'existe dans la base de données avec l'ID fourni")
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des clients", description = "Cette méthode permet de rechercher et de renvoyer la liste des clients qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des clients / Une liste vide")
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @Operation(summary = "Supprimer un client", description = "Cette méthode permet de supprimer un client par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le client a été supprimé dans la base de données")
    })
    void delete(@PathVariable("idClient") Integer id);

}

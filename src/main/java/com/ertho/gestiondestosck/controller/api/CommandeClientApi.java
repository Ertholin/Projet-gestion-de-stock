package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.CommandeClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/commandeClients")
public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandeClients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une commande client", description = "Cette méthode permet d'enregisrer ou modifier une commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet commande Client créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet commande Client n'est pas valide")
    })
    CommandeClientDto save(@RequestBody CommandeClientDto dto);

    @GetMapping(value = APP_ROOT + "/commandeClient/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande client par ID", description = "Cette méthode permet de rechercher une commande client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client n'existe dans la base de données avec l'ID fourni")
    })
    CommandeClientDto findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(value = APP_ROOT + "/commandeClients/{codeCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande client par code", description = "Cette méthode permet de rechercher une commande client par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client n'existe dans la base de données avec le CODE fourni")
    })
    CommandeClientDto findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(value = APP_ROOT + "/commandeClients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des commandes clients", description = "Cette méthode permet de rechercher et de renvoyer la liste des commandes clients qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des commandes clients / Une liste vide")
    })
    List<CommandeClientDto> findAll();


    @DeleteMapping(value = APP_ROOT + "/commandeClient/delete/{idCommandeClient}")
    @Operation(summary = "Supprimer une commande client", description = "Cette méthode permet de supprimer une commande client par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a été supprimée dans la base de données")
    })
    void delete(@PathVariable("idCommandeClient") Integer id);

}

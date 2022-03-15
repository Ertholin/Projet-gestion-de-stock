package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.CommandeFournisseurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/commandeFournisseurs")
public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "/commandeFournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une commande fournisseur", description = "Cette méthode permet d'enregisrer ou modifier une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet commande fournisseur créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet commande fournisseur n'est pas valide")
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/{idCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande fournisseur par ID", description = "Cette méthode permet de rechercher une commande fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/{codeCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande fournisseur par code", description = "Cette méthode permet de rechercher une commande fournisseur par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec le CODE fourni")
    })
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des commandes fournisseur", description = "Cette méthode permet de rechercher et de renvoyer la liste des commandes fournisseur qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des commandes fournisseurs / Une liste vide")
    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandeFournisseur/delete/{idCommandeFournisseur}")
    @Operation(summary = "Supprimer une commande fournisseur", description = "Cette méthode permet de supprimer une commande fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a été supprimée dans la base de données")
    })
    void delete(@PathVariable("idCommandeFournisseur") Integer id);

}

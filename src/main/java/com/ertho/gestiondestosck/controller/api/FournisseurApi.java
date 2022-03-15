package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.FournisseurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/fournisseurs")
public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un fournisseur", description = "Cette méthode permet d'enregistrer ou modifier un fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet Fournisseur créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet Fournisseur n'est pas valide")
    })
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une fournisseur par ID", description = "Cette méthode permet de rechercher un fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur a été trouvé dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucun fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des fournisseurs", description = "Cette méthode permet de rechercher et de renvoyer la liste des fournisseurs qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des fournisseurs / Une liste vide")
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
    @Operation(summary = "Supprimer un fournisseur", description = "Cette méthode permet de supprimer un fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur a été supprimé dans la base de données")
    })
    void delete(@PathVariable("idFournisseur") Integer id);

}

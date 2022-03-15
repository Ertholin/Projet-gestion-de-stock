package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.EntrepriseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/enterprises")
public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/enterprises/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une entreprise", description = "Cette méthode permet d'enregisrer ou de modifier une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet Entreprise créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet Entreprise n'est pas valide")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une entreprise par ID", description = "Cette méthode permet de rechercher une entreprise par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'entreprise a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune entreprise n'existe dans la base de données avec l'ID fourni")
    })
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprises/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des entreprises", description = "Cette méthode permet de rechercher et de renvoyer la liste des entreprises qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des entreprises / Une liste vide")
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{idEntreprise}")
    @Operation(summary = "Supprimer une entreprise", description = "Cette méthode permet de supprimer une entreprise par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'entreprise a été supprimée dans la base de données")
    })
    void delete(@PathVariable("idEntreprise") Integer id);

}

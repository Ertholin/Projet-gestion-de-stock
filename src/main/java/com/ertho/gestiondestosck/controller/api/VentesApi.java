package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.VentesDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/ventes")
public interface VentesApi {

    @PostMapping(value = APP_ROOT + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une vente", description = "Cette méthode permet d'enregisrer ou modifier une vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet Vente créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet Vente n'est pas valide")
    })
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(value = APP_ROOT + "/ventes/{idVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une vente par ID", description = "Cette méthode permet de rechercher une vente par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune vente n'existe dans la base de données avec l'ID fourni")
    })
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{codeVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une vente par code", description = "Cette méthode permet de rechercher une vente par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune vente n'existe dans la base de données avec le CODE fourni")
    })
    VentesDto findVenteByCode(@PathVariable("codeVente") String code);

    @GetMapping(value = APP_ROOT + "/ventes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des ventes", description = "Cette méthode permet de rechercher et de renvoyer la liste des ventes qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des ventes / Une liste vide")
    })
    List<VentesDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{idVente}")
    @Operation(summary = "Supprimer une vente", description = "Cette méthode permet de supprimer une vente par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a été supprimée dans la base de données")
    })
    void delete(@PathVariable("idVente") Integer id);
}

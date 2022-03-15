package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.UtilisateurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Tag(name = APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un utilisateur", description = "Cette méthode permet d'enregisrer ou modifier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet Utilisateur créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet Utilisateur n'est pas valide")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un utilisateur par ID", description = "Cette méthode permet de rechercher un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'Utilisateur a été trouvé dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur n'existe dans la base de données avec l'ID fourni")
    })
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des utilisateurs", description = "Cette méthode permet de rechercher et de renvoyer la liste des utilisateurs qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des utilisateurs / Une liste vide")
    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    @Operation(summary = "Supprimer un utilisateur", description = "Cette méthode permet de supprimer un utilisateur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur a été supprimé dans la base de données")
    })
    void delete(@PathVariable("idUtilisateur") Integer id);
}

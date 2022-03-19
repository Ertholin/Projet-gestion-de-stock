package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@RestController
@Tag(name = APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une catégorie", description = "Cette méthode permet d'enregisrer ou modifier une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet Catégory créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet Catégory n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto dto);


    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une Catégorie par ID", description = "Cette méthode permet de rechercher une catégorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie n'existe dans la base de données avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une catégorie par code", description = "Cette méthode permet de rechercher une catégorie par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie n'existe dans la base de données avec le CODE fourni")
    })
    CategoryDto findByCodeCategory(@Parameter(description = "Accepted value [Cat, Cat1, cat2]") @PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des catégories", description = "Cette méthode permet de rechercher et de renvoyer la liste des catégories qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des catégories / Une liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer une catégorie", description = "Cette méthode permet de supprimer une catégorie par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été supprimée dans la base de données")
    })
    void delete(@PathVariable("idCategory") Integer id);

}

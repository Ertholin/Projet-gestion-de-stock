package com.ertho.gestiondestosck.controller.api;

import com.ertho.gestiondestosck.dto.CommandeClientDto;
import com.ertho.gestiondestosck.dto.LigneCommandeClientDto;
import com.ertho.gestiondestosck.model.EtatCommande;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @PatchMapping(value = APP_ROOT + "/commandeClients/update/etat/{idCommande}/{etatCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value = APP_ROOT + "/commandeClients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> updateQuantiteCommandee(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande")Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value = APP_ROOT + "/commandeClients/update/client/{idCommande}/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient")Integer idClient);

    @PatchMapping(value = APP_ROOT + "/commandeClients/update/article/{idCommande}/{idLigneCommande}/{idArticle}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande")Integer idCommande, @PathVariable("idLigneCommande")Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = APP_ROOT + "/commandeClients/delete/article/{idCommande}/{idLigneCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande")Integer idCommande, @PathVariable("idLigneCommande")Integer idLigneCommande);

    @GetMapping(value = APP_ROOT + "/commandeClient/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande client par ID", description = "Cette méthode permet de rechercher une commande client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client n'existe dans la base de données avec l'ID fourni")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(value = APP_ROOT + "/commandeClients/{codeCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande client par code", description = "Cette méthode permet de rechercher une commande client par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a été trouvée dans la base de données"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client n'existe dans la base de données avec le CODE fourni")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(value = APP_ROOT + "/commandeClients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des commandes clients", description = "Cette méthode permet de rechercher et de renvoyer la liste des commandes clients qui existe " +
            "dans la Base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des commandes clients / Une liste vide")
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @GetMapping(value = APP_ROOT + "/commandeClients/lignesCommande/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);


    @DeleteMapping(value = APP_ROOT + "/commandeClient/delete/{idCommandeClient}")
    @Operation(summary = "Supprimer une commande client", description = "Cette méthode permet de supprimer une commande client par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a été supprimée dans la base de données")
    })
    ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);

}

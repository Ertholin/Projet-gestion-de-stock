package com.ertho.gestiondestosck.controller;

import com.ertho.gestiondestosck.controller.api.ArticleApi;
import com.ertho.gestiondestosck.dto.ArticleDto;
import com.ertho.gestiondestosck.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    //    Field Injection
    //    @Autowired
    private ArticleService articleService;

    //Getter Injection
    //    @Autowired
    //    public ArticleService getArticleService(){
    //        return articleService;
    //    }

    /**
     * Constructor Injection
     * Permet d'injecter des instances a chaque fois qu'on trouve cette annotion(Autres: Fileld Injection, Constructor Injection, Getter jection)
     * @param articleService
     */
    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }
}

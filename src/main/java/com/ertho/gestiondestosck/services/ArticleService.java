package com.ertho.gestiondestosck.services;

import com.ertho.gestiondestosck.dto.ArticleDto;
import com.ertho.gestiondestosck.model.Article;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto dto);

    ArticleDto findById(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto> findAll();

    void delete(Integer id);
}

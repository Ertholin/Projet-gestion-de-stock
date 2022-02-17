package com.ertho.gestiondestosck.repository;

import com.ertho.gestiondestosck.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /*
    // Requetes JPSQL
    @Query("select a from article where codearticle =: code and designation =: designation")
    List<Article> findByCustomQuery(@Param("code") String c, @Param("designation") String d);

    // Requetes Natives
    @Query(value = "select * from article where code = :code", nativeQuery = true)
    List<Article> findByCustomNativeQuery(@Param("code") String c);

    // Requetes Simples
    List<Article> findByCodeIgnoreCaseAndDesignationIgnoreCase(String code, String designation);
     */

    Optional<Article> findArticleByCodeArticle(String codeArticle);

}

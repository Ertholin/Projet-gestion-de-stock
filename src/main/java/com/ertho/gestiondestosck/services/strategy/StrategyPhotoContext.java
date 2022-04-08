package com.ertho.gestiondestosck.services.strategy;

import com.ertho.gestiondestosck.exception.ErrorCodes;
import com.ertho.gestiondestosck.exception.InvalidOperationException;
import com.flickr4java.flickr.FlickrException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {

    /**
     * On utilise le BeanFactory de Spring parce qu'on ne sait pas reellement quelle injection
     * faire sur Strategy, car elle implementee dans plusieurs classes
     */
    private BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determineContext(context);
        return strategy.savePhoto(id, photo, title);
    }

    private void determineContext(String context){
        final String beanName = context + "Strategy";
        switch (context){
            case "article":
                strategy = beanFactory.getBean("", SaveArticlePhoto.class);
                break;
            case "client":
                strategy = beanFactory.getBean("", SaveClientPhoto.class);
                break;
            case "fournisseur":
                strategy = beanFactory.getBean("", SaveFournisseurPhoto.class);
                break;
            case "entreprise":
                strategy = beanFactory.getBean("", SaveEntreprisePhoto.class);
                break;
            case "utilisateur":
                strategy = beanFactory.getBean("", SaveUtilisateurPhoto.class);
                break;
            default:
                throw new InvalidOperationException("Contexte inconnue pour l'enregistrement de la photo", ErrorCodes.UNKNOWN_CONTEXT);
        }
    }
}

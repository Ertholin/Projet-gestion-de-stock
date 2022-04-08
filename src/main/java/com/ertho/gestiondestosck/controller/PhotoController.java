package com.ertho.gestiondestosck.controller;

import com.ertho.gestiondestosck.controller.api.PhotoApi;
import com.ertho.gestiondestosck.services.strategy.StrategyPhotoContext;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController implements PhotoApi {

    private StrategyPhotoContext photoContext;

    @Autowired
    public PhotoController(StrategyPhotoContext photoContext) {
        this.photoContext = photoContext;
    }

    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return photoContext.savePhoto(context, id, photo.getInputStream(), title);
    }
}

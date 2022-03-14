package com.ertho.gestiondestosck.services.impl;

import com.ertho.gestiondestosck.services.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {

    private Flickr flickr;

    @Autowired
    public FlickrServiceImpl(Flickr flickr) {
        this.flickr = flickr;
    }

    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {
        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }

    /**
     * Methode qui permet de se connecter à notre son API
     */

    /**
     * private void connect(){
     *
     *         flickr = new Flickr(apiKey, apiSecret, new REST());
     *
     *         Auth auth = new Auth();
     *
     *         auth.setPermission(Permission.DELETE);
     *
     *         auth.setToken(appKey);
     *         auth.setTokenSecret(appSecret);
     *
     *         RequestContext requestContext = new RequestContext();
     *         requestContext.setAuth(auth);
     *
     *         flickr.setAuth(auth);
     *
     *     }
     */

}

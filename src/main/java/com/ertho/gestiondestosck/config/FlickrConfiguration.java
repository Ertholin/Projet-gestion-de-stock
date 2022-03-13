package com.ertho.gestiondestosck.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.apis.FlickrApi.FlickrPerm;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Classe de configuration d'API Flickr pour le stockage des photos sur le cloud.
 */

//@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    /**
     * Creation d'une instance de type Flickr en utilisant l'ApiKey, l'ApiSecret et passe en paramètre un
     *          de type REST.
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws FlickrException
     */
    @Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        /**
         * Creation d'un objet de type OAuth10aService avec les permissions de l'application
         */
        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrPerm.DELETE));

        /**
         * Necessite un objet de type Scanner pour l'utilisation de l'autorisation qui sera fournie
         * par Flickr pour generer l'AppTocken et ApiSecret
         */
        final Scanner scanner = new Scanner(System.in);

        final OAuth1RequestToken request = service.getRequestToken();

        final String authUrl = service.getAuthorizationUrl(request);

        System.out.println(authUrl);
        System.out.println("Paste it here >> ");

        final String authVerifier = scanner.nextLine();

        OAuth1AccessToken accessToken = service.getAccessToken(request, authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());

        return flickr;
    }
}

/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.proevan.nanodegree.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.proevan.Joke;
import com.proevan.JokeData;
import com.proevan.JokeTeller;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.nanodegree.proevan.com",
                ownerName = "backend.builditbigger.nanodegree.proevan.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    private static JokeTeller sJokeTeller;

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        if (sJokeTeller == null) {
            sJokeTeller = new JokeTeller();
        }
        return sJokeTeller.getJoke();
    }

}

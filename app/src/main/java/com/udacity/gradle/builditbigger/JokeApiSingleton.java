package com.udacity.gradle.builditbigger;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.proevan.nanodegree.builditbigger.backend.jokeApi.JokeApi;

public class JokeApiSingleton {

    private static JokeApi sInstance;

    public static JokeApi getInstance() {
        if (sInstance == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://android-nanodegree.appspot.com/_ah/api/");
            sInstance = builder.build();
        }

        return sInstance;
    }

    private JokeApiSingleton() {
    }
}

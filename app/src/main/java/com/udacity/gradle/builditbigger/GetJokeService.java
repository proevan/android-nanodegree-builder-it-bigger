package com.udacity.gradle.builditbigger;

import android.util.Log;

import com.proevan.nanodegree.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

public class GetJokeService {

    private JokeApi mJokeApiService;

    public GetJokeService(JokeApi jokeApiService) {
        mJokeApiService = jokeApiService;
    }

    public void getJoke(final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    result =  mJokeApiService.getJoke().execute().getText();
                } catch (IOException e) {
                    Log.e("GetJokeService", "getJoke IOException occured");
                }
                callback.onComplete(result);
            }
        }).start();
    }

    public interface Callback {
        void onComplete(String jokeText);
    }
}

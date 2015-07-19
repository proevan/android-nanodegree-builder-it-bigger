package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.proevan.nanodegree.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

public class GetJokeService {

    private JokeApi mJokeApiService;

    public GetJokeService(JokeApi jokeApiService) {
        mJokeApiService = jokeApiService;
    }

    public void getJoke(final Callback callback) {
        new GetJokeAsyncTask(mJokeApiService, callback).execute();
    }

    static class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

        private JokeApi mJokeApiService;
        private Callback mCallback;

        public GetJokeAsyncTask(JokeApi jokeApiService, Callback callback) {
            mJokeApiService = jokeApiService;
            mCallback = callback;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return mJokeApiService.getJoke().execute().getText();
            } catch (IOException e) {
                Log.e("GetJokeService", "getJoke IOException occured");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mCallback.onComplete(result);
        }
    }

    public interface Callback {
        void onComplete(String jokeText);
    }
}

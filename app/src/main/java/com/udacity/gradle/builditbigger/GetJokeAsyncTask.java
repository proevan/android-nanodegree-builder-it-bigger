package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.proevan.jokecontainer.JokeContainerActivity;
import com.proevan.nanodegree.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static JokeApi myApiService = null;
    private Context mContext;
    private ProgressBarCircularIndeterminate mProgressBar;

    public GetJokeAsyncTask(Context context, ProgressBarCircularIndeterminate progressDialog) {
        mContext = context;
        mProgressBar = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://android-nanodegree.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getText();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onCancelled() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPostExecute(String result) {
        mProgressBar.setVisibility(View.GONE);
        showJokePage(result);
    }

    private void showJokePage(String jokeText) {
        Intent launchIntent = JokeContainerActivity.getCallingIntent(mContext, jokeText);
        mContext.startActivity(launchIntent);
    }
}

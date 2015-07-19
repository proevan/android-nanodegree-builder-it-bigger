package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.proevan.jokecontainer.JokeContainerActivity;
import com.proevan.nanodegree.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    private ProgressBarCircularIndeterminate mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProgressDialog();
    }

    private void initProgressDialog() {
        mProgressBar = (ProgressBarCircularIndeterminate) findViewById(R.id.progress_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTellJokeButtonClick(View view){
        if (!isLoading()) {
            new GetJokeAsyncTask(this, mProgressBar).execute();
        }
    }

    private boolean isLoading() {
        return mProgressBar.getVisibility() == View.VISIBLE;
    }

    static class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

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
}

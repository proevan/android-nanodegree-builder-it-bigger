package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.proevan.jokecontainer.JokeContainerActivity;

public class MainActivity extends ActionBarActivity {

    private GetJokeService mGetJokeService;
    private ProgressBarCircularIndeterminate mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGetJokeService = new GetJokeService(JokeApiSingleton.getInstance());
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
            mProgressBar.setVisibility(View.VISIBLE);
            mGetJokeService.getJoke(new GetJokeService.Callback() {
                @Override
                public void onComplete(String jokeText) {
                    mProgressBar.setVisibility(View.GONE);
                    showJokePage(jokeText);
                }
            });
        }
    }

    private boolean isLoading() {
        return mProgressBar.getVisibility() == View.VISIBLE;
    }

    private void showJokePage(String jokeText) {
        Intent launchIntent = JokeContainerActivity.getCallingIntent(this, jokeText);
        startActivity(launchIntent);
    }
}

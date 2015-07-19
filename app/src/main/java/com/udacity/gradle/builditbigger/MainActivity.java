package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.proevan.JokeTeller;
import com.proevan.jokecontainer.JokeContainerActivity;


public class MainActivity extends ActionBarActivity {

    private JokeTeller mJokeTeller = new JokeTeller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void showJoke(View view){
        String joke = mJokeTeller.getJoke();
        if (joke != null) {
            Intent launchIntent = JokeContainerActivity.getCallingIntent(this, joke);
            startActivity(launchIntent);
        } else {
            Toast.makeText(MainActivity.this, R.string.message_no_joke, Toast.LENGTH_SHORT).show();
        }
    }


}

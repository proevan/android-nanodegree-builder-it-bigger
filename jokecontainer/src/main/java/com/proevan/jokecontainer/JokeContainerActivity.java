package com.proevan.jokecontainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeContainerActivity extends AppCompatActivity {

    private static final String INTENT_PARAM_JOKE = "INTENT_PARAM_JOKE";

    public static Intent getCallingIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeContainerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(INTENT_PARAM_JOKE, joke);
        intent.putExtras(bundle);

        return intent;
    }

    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mJoke = bundle.getString(INTENT_PARAM_JOKE);
        }
        setContentView(R.layout.activity_joke_container);
        initLayout();
    }

    private void initLayout() {
        TextView jokeTextView = (TextView) findViewById(R.id.joke);
        jokeTextView.setText(mJoke);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke_container, menu);
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
}

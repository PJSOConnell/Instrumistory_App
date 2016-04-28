package com.example.pj.instrumistory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Settings");
    }

    public void goToReferences(View view) {

        Intent intentSet = new Intent(this, ReferenceActivity.class);
        startActivity(intentSet);
    }

    public void backToHome(View view) {

        Intent intentSet = new Intent(this, MainActivity.class);
        startActivity(intentSet);
    }
}


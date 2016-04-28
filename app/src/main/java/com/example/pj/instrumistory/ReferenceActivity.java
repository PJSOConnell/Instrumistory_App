package com.example.pj.instrumistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ReferenceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reference);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.ref_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("References");
    }

    public void goBack(View view) {
        Intent intentHome = new Intent(this, settingsActivity.class);
        startActivity(intentHome);
    }
}
package com.aconst.money4life;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ActionsActivity extends AppCompatActivity {
    static final int ADD_REQUEST = 0;
    static final int EDIT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabActions = findViewById(R.id.fabActions);
        fabActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActionsActivity.this,
                        ActionNewEditActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });
    }

}

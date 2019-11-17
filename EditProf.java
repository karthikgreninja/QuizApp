package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prof);
        Button add = (Button)findViewById(R.id.btnAddProf);
        Button del = (Button)findViewById(R.id.btnDelProf);
        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),NewProf.class);
                startActivity(i);
            }
        });

        del.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DelProf.class);
                startActivity(i);
            }
        });
    }
}

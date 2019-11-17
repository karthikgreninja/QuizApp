package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        Button AddCourse = (Button)findViewById(R.id.btnAddCourse);
        Button DelCourse = (Button)findViewById(R.id.btnDelCourse);
        AddCourse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),NewCourse.class);
                startActivity(i);
            }
        });

        DelCourse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DeleteCourse.class);
                startActivity(i);
            }
        });
    }
}

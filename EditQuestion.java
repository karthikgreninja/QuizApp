package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditQuestion extends AppCompatActivity {
    private Button add;
    private Button del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);
        Intent intent = getIntent();
        final String LoginId = intent.getStringExtra("arg");
        add = (Button)findViewById(R.id.btnAddQ);
        del = (Button)findViewById(R.id.btnDelQ);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditQuestion.this,ImportQuestions.class);
                Bundle extras = new Bundle();
                extras.putString("arg",LoginId);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditQuestion.this,DelQuestion.class);
                Bundle extras = new Bundle();
                extras.putString("arg",LoginId);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}

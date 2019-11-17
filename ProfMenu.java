package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfMenu extends AppCompatActivity {
    private Button results;
    private Button QuestionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_menu);
        results = (Button)findViewById(R.id.btnViewResult);
        Intent intent = getIntent();
        final String LoginId = intent.getStringExtra("arg");
        QuestionEdit = (Button)findViewById(R.id.btnEditQuestions);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfMenu.this,Prof.class);
                Bundle extras = new Bundle();
                extras.putString("arg",LoginId);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        QuestionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfMenu.this,EditQuestion.class);
                Bundle extras = new Bundle();
                extras.putString("arg",LoginId);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}

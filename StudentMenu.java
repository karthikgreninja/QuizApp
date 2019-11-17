package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentMenu extends AppCompatActivity {

    Button quiz;
    Button result;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        quiz = (Button)findViewById(R.id.btnQuiz);
        result = (Button)findViewById(R.id.btnResult);
        textView = (TextView)findViewById(R.id.tvStudentMenu);
        Intent intent = getIntent();
        final String RollNo = intent.getStringExtra("arg");
        String message = "Hi ";
        message = message + RollNo;
        message = message + ", please select one from the given options";
        textView.setText(message);
        quiz.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Quiz.class);
                i.putExtra("arg",RollNo);
                startActivity(i);
            }
        });
        result.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),StudentConsolidatedResult.class);
                i.putExtra("arg",RollNo);
                startActivity(i);
            }
        });
    }
}

package com.example.karthikeyan.karthikphplogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    private EditText user_roll;
    private EditText user_pass;
    String roll;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_roll = (EditText)findViewById(R.id.etRollNo);
        user_pass = (EditText)findViewById(R.id.etPassword);
    }

    public void userReg(View view){
        roll = user_roll.getText().toString();
        pass = user_pass.getText().toString();
        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,roll,pass);
        finish();
    }
}

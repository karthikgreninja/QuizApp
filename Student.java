package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Student extends AppCompatActivity {

    private EditText RollNo ;
    private EditText Password;
    private Button Login;
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkFieldsForEmptyValues();
        }
    };
    String login_roll;
    String login_pass;
    void checkFieldsForEmptyValues(){
        String s1 = RollNo.getText().toString();
        String s2 = Password.getText().toString();
        if(s1.isEmpty() || s2.isEmpty()) {
            Login.setEnabled(false);
        }
        else {
            Login.setEnabled(true);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        RollNo = (EditText)findViewById(R.id.etRollNo);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);

        /*Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Validate(RollNo.getText().toString(), Password.getText().toString());
            }
        });*/
        RollNo.addTextChangedListener(mTextWatcher);
        Password.addTextChangedListener(mTextWatcher);
        checkFieldsForEmptyValues();
    }

    public void userLogin(View view){
        login_roll = RollNo.getText().toString();
        login_pass = Password.getText().toString();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,login_roll,login_pass);
    }

}
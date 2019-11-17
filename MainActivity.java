package com.example.karthikeyan.karthikphplogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] items = new String[]{"Student","Professor","Admin"};
    private EditText RollNo ;
    private EditText Password;
    private Button Login;
    Spinner spinner;
    String text;
    String backConfirm="No";
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
        setContentView(R.layout.activity_main);
        RollNo = (EditText)findViewById(R.id.etRollNo);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        spinner = (Spinner)findViewById(R.id.spinnerLoginType);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items));
        RollNo.addTextChangedListener(mTextWatcher);
        Password.addTextChangedListener(mTextWatcher);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                text = String.valueOf(spinner.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        checkFieldsForEmptyValues();
    }

    public void userLogin(View view){
        login_roll = RollNo.getText().toString();
        login_pass = Password.getText().toString();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,text,login_roll,login_pass);
    }

    @Override
    public void onBackPressed() {
        if(backConfirm.equals("Yes")){
            super.onBackPressed();
        }
        Toast.makeText(this, "Press Back again to Exit.",
                Toast.LENGTH_SHORT).show();
        backConfirm="Yes";

    }

}
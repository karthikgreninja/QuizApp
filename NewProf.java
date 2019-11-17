package com.example.karthikeyan.karthikphplogin;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewProf extends AppCompatActivity {
    HttpURLConnection httpURLConnection;
    EditText ProfId,Fname,Mname,Lname,Pass,Dept;
    String PId,FN,MN,LN,PASS,DEPT,line,result;
    Button add;
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prof);
        ProfId = (EditText) findViewById(R.id.etAddProfId);
        Fname = (EditText) findViewById(R.id.etAddProfFname);
        Mname = (EditText) findViewById(R.id.etAddProfMname);
        Lname = (EditText) findViewById(R.id.etAddProfLname);
        Pass = (EditText)findViewById(R.id.etAddProfPass);
        Dept = (EditText)findViewById(R.id.etAddProfDept);
        add = (Button) findViewById(R.id.btnAddProf);
        ProfId.addTextChangedListener(mTextWatcher);
        Fname.addTextChangedListener(mTextWatcher);
        Mname.addTextChangedListener(mTextWatcher);
        Lname.addTextChangedListener(mTextWatcher);
        Pass.addTextChangedListener(mTextWatcher);
        Dept.addTextChangedListener(mTextWatcher);
        checkFieldsForEmptyValues();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PId = ProfId.getText().toString();
                FN = Fname.getText().toString();
                MN = Mname.getText().toString();
                LN = Lname.getText().toString();
                PASS = Pass.getText().toString();
                DEPT = Dept.getText().toString();
                try {
                    String profURL = "http://192.168.43.235/QuizApp/NewProf.php?String1=" + PId + "&String2=" + FN + "&String3=" + MN +"&String4="+ LN +"&String5="+ PASS + "&String6="+ DEPT;
                    String URLUpdate = profURL.replace(" ","%20");
                    URL url = new URL(URLUpdate);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    is = httpURLConnection.getInputStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line + '\n');
                    }
                    is.close();
                    result = stringBuilder.toString();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                displayToastMsg(v);
            }
        });
    }
    public void displayToastMsg(View v) {

        toastMsg(result);

    }
    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();

    }
    void checkFieldsForEmptyValues() {
        String s1 = ProfId.getText().toString();
        String s2 = Fname.getText().toString();
        String s3 = Mname.getText().toString();
        String s4 = Lname.getText().toString();
        String s5 = Pass.getText().toString();
        String s6 = Dept.getText().toString();
        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty() || s6.isEmpty()) {
            add.setEnabled(false);
        } else {
            add.setEnabled(true);
        }
    }
}

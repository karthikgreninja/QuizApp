package com.example.karthikeyan.karthikphplogin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewCourse extends AppCompatActivity {

    HttpURLConnection httpURLConnection;
    String CID,line,result;
    String CName;
    Button add;
    EditText CourseId;
    EditText CourseName;
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
        setContentView(R.layout.activity_new_course);
        CourseId = (EditText) findViewById(R.id.etAddCourseId);
        CourseName = (EditText) findViewById(R.id.etAddCourseName);
        CourseId.addTextChangedListener(mTextWatcher);
        CourseName.addTextChangedListener(mTextWatcher);
        add = (Button) findViewById(R.id.btnAddCourse);
        checkFieldsForEmptyValues();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    CID = CourseId.getText().toString();
                    CName = CourseName.getText().toString();
                    try {
                        String courseURL = "http://192.168.43.235/QuizApp/NewCourse.php?String1=" + CID + "&String2=" + CName;
                        String URLUpdate = courseURL.replace(" ","%20");
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
        String s1 = CourseId.getText().toString();
        String s2 = CourseName.getText().toString();
        if (s1.isEmpty() || s2.isEmpty()) {
            add.setEnabled(false);
        } else {
            add.setEnabled(true);
        }
    }
}
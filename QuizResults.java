package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class QuizResults extends AppCompatActivity {
    HttpURLConnection httpURLConnection;
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    private TextView textView;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton Quiz1;
    private RadioButton Quiz2;
    private RadioButton Both;
    private String RollNo;
    private String CId;
    private String line = null;
    private String result = "";
    private String result1 = "";

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        RollNo = getIntent().getExtras().getString("RollNo");
        CId = getIntent().getExtras().getString("Course");
        textView = (TextView) findViewById(R.id.tvResultOption);
        textView.setText("Please select the test for which you wish to view results");
        button = (Button) findViewById(R.id.btnQuizResult);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupQuiz);
        Quiz1 = (RadioButton) findViewById(R.id.radioQ1);
        Quiz2 = (RadioButton) findViewById(R.id.radioQ2);
        Both = (RadioButton) findViewById(R.id.radioBoth);
        Quiz1.setText("Quiz 1");
        Quiz2.setText("Quiz 2");
        Both.setText("Both Quiz 1 and Quiz 2");
        Both.setEnabled(false);
        Quiz2.setEnabled(false);
        Quiz1.setEnabled(false);
        button.setEnabled(false);
        try {
            URL url = new URL("http://192.168.43.235/QuizApp/Q1Enabled.php?String1=" + RollNo + "&String2=" +  CId);
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
        if(result.contains("Success")){
            Quiz1.setEnabled(true);
        }
        try {
            URL url = new URL("http://192.168.43.235/QuizApp/Q2Enabled.php?String1=" + RollNo + "&String2=" +  CId);
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
            result1 = stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result1.contains("Success")){
            Quiz2.setEnabled(true);
        }
        if(result.contains("Success") && result1.contains("Success")){
            Both.setEnabled(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setEnabled(true);
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                String response = radioButton.getText().toString();
                Intent intent=new Intent(QuizResults.this,QuizResultView.class);
                Bundle extras = new Bundle();
                extras.putString("RollNo",RollNo);
                extras.putString("Course",CId);
                extras.putString("Response",response);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}

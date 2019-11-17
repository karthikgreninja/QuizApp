package com.example.karthikeyan.karthikphplogin;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Float.parseFloat;

public class QuizResultView extends AppCompatActivity {
    String RollNo;
    String CId;
    String response;
    String name[];
    String score[];
    HttpURLConnection httpURLConnection;
    studentQuizResult studentResult[];
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result_view);
        ListView mlistView = (ListView)findViewById(R.id.listViewQuiz);
        RollNo = getIntent().getExtras().getString("RollNo");
        CId = getIntent().getExtras().getString("Course");
        response = getIntent().getExtras().getString("Response");
        String line = null;
        String result = "";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        name = new String[2];
        score = new String[2];
        float SCORE[];
        name[0] = "Quiz1";
        name[1] = "Quiz2";
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("http://192.168.43.235/QuizApp/Q1Results.php?String1=" + RollNo + "&String2=" + CId);
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

        try {
            JSONArray JA = new JSONArray(result);
            JSONObject jsonObject = null;
            jsonObject = JA.getJSONObject(0);
            score[0] = jsonObject.getString("TestScore");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL("http://192.168.43.235/QuizApp/Q2Results.php?String1=" + RollNo + "&String2=" + CId);
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

        try {
            JSONArray JA = new JSONArray(result);
            JSONObject jsonObject = null;
            jsonObject = JA.getJSONObject(0);
            score[1] = jsonObject.getString("TestScore");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(response.equals("Both Quiz 1 and Quiz 2")){
            SCORE = new float[score.length];
            for(int i=0;i<score.length;i++){
                SCORE[i] = parseFloat(score[i]);
            }
            studentQuizResult studentResult[];
            studentResult =  new studentQuizResult[2];
            for(int z=0;z<score.length;z++){
                studentResult[z] = new studentQuizResult(name[z],SCORE[z]);
            }
            ArrayList<studentQuizResult> studentList = new ArrayList<>();
            for(int i=0;i<score.length;i++){
                studentList.add(studentResult[i]);
            }
            studentResultListAdapter adapter = new studentResultListAdapter(this,R.layout.adapter_view_layout,studentList);
            mlistView.setAdapter(adapter);
        }
        else if(response.equals("Quiz 1")){
            SCORE = new float[1];
            SCORE[0] = parseFloat(score[0]);
            studentQuizResult studentResult[];
            studentResult =  new studentQuizResult[1];
            studentResult[0] = new studentQuizResult(name[0],SCORE[0]);
            ArrayList<studentQuizResult> studentList = new ArrayList<>();
            studentList.add(studentResult[0]);
            studentResultListAdapter adapter = new studentResultListAdapter(this,R.layout.adapter_view_layout,studentList);
            mlistView.setAdapter(adapter);
        }
        else if(response.equals("Quiz 2")){
            SCORE = new float[1];
            SCORE[0] = parseFloat(score[1]);
            studentQuizResult studentResult[];
            studentResult =  new studentQuizResult[1];
            studentResult[0] = new studentQuizResult(name[1],SCORE[0]);
            ArrayList<studentQuizResult> studentList = new ArrayList<>();
            studentList.add(studentResult[0]);
            studentResultListAdapter adapter = new studentResultListAdapter(this,R.layout.adapter_view_layout,studentList);
            mlistView.setAdapter(adapter);
        }




    }
}

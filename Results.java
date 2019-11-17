package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
import java.util.List;

import static java.lang.Float.parseFloat;

public class Results extends AppCompatActivity {
    HttpURLConnection httpURLConnection = null;
    String name[];
    String score[];
    String text;
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        ListView mlistView = (ListView)findViewById(R.id.listViewStudent);
        final String TId = intent.getStringExtra("TestId");
        final String BatchId = intent.getStringExtra("BatchId");
        final String CId = intent.getStringExtra("CourseId");
        String line = null;
        String result = "";
        float SCORE[];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL url = new URL("http://192.168.43.235/QuizApp/ResultsRetrieveProf.php?String1=" + TId + "&String2=" + BatchId + "&String3=" + CId);
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
            name = new String[JA.length()];
            score = new String[JA.length()];
            for (int i = 0; i < JA.length(); i++) {
                jsonObject = JA.getJSONObject(i);
                name[i] = jsonObject.getString("Rno");
                score[i] = jsonObject.getString("TestScore");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SCORE = new float[score.length];
        for(int i=0;i<score.length;i++){
            SCORE[i] = parseFloat(score[i]);
        }
        StudentResult studentResult[];
        studentResult =  new StudentResult[name.length];
        for(int z=0;z<name.length;z++){
            studentResult[z] = new StudentResult(name[z],SCORE[z]);
        }
        System.out.println(studentResult[0]);

        ArrayList<StudentResult> studentList = new ArrayList<>();
        for(int i=0;i<name.length;i++){
            studentList.add(studentResult[i]);
        }

        studentListAdapter adapter = new studentListAdapter(this,R.layout.adapter_viw_layout,studentList);
        mlistView.setAdapter(adapter);





    }
}

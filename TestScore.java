package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class TestScore extends AppCompatActivity {
HttpURLConnection httpURLConnection = null;
String line;
String count;
String COUNT;
int numberOfCorrect;
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_score);
        TextView textView = findViewById(R.id.tvScore);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String Roll = extras.getString("RollNo");
        String TId = extras.getString("TId");
        String CId = extras.getString("CId");
        int No = extras.getInt("No");
        String results = "Congrats ";
        results = results + Roll;
        results = results + ", you have successfully completed the test : ";
        results = results + TId;
        results = results + " for the course ";
        results = results + CId;
        results = results + " with ";
        try {
            URL url = new URL("http://192.168.43.235/QuizApp/countCorrectResponse.php?String1=" + Roll + "&String2=" + TId);
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
            count = stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONArray JA = new JSONArray(count);
            JSONObject jsonObject = JA.getJSONObject(0);
            COUNT = jsonObject.getString("count(*)");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        results = results + COUNT;
        results = results + " correct out of ";
        results = results + No;
        textView.setText(results);
        try {
            URL url = new URL("http://192.168.43.235/QuizApp/updateTestScore.php?String1=" + Roll + "&String2=" + CId + "&String3=" + TId +"&String4="+COUNT);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

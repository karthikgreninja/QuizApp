package com.example.karthikeyan.karthikphplogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.util.ArrayList;

public class StudentConsolidatedResult extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    TextView textView;
    String name[];
    Spinner spinner;
    Button button;
    String text;
    HttpURLConnection httpURLConnection;
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_consolidated_result);
        Intent intent = getIntent();
        final String RollNo = intent.getStringExtra("arg");
        spinner = (Spinner)findViewById(R.id.spCourseResult);
        button = (Button)findViewById(R.id.btnViewResults);
        textView = (TextView)findViewById(R.id.tvRollResult);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listItems);
        spinner.setAdapter(adapter);
        String message = "Please select the course";
        textView.setText(message);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String line=null;
        String result="";
        try {
            URL url = new URL("http://192.168.43.235/QuizApp/ResultsCourseRetrieve.php?String1="+RollNo);
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
        System.out.println(result);

        try {
            JSONArray JA = new JSONArray(result);
            JSONObject jsonObject = null;
            name = new String[JA.length()];
            for (int i = 0; i < JA.length(); i++) {
                jsonObject = JA.getJSONObject(i);
                name[i] = jsonObject.getString("CId");
            }
            for (int i = 0; i < name.length; i++) {
                listItems.add(name[i]);
            }
            spinner_fn();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        button.setEnabled(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                text = String.valueOf(spinner.getSelectedItem());
                button.setEnabled(true);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentConsolidatedResult.this,QuizResults.class);
                Bundle extras = new Bundle();
                extras.putString("RollNo",RollNo);
                extras.putString("Course",text);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }

    private void spinner_fn() {
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(StudentConsolidatedResult.this, android.R.layout.simple_spinner_item, name);
        spinner.setAdapter(dataAdapter1);
    }
}

package com.example.karthikeyan.karthikphplogin;

import android.app.AlertDialog;
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

public class DelQuestion extends AppCompatActivity {
    HttpURLConnection httpURLConnection = null;
    private Button button;
    private EditText editText;
    String QID,line,result;
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
        setContentView(R.layout.activity_del_question);
        button = (Button)findViewById(R.id.btnDelQuestion);
        editText = (EditText)findViewById(R.id.etEnterQId);
        editText.addTextChangedListener(mTextWatcher);
        checkFieldsForEmptyValues();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QID = editText.getText().toString();
                try {
                    URL url = new URL("http://192.168.43.235/QuizApp/DelQuestion.php?String1=" + QID);
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
        String s1 = editText.getText().toString();
        if (s1.isEmpty()) {
            button.setEnabled(false);
        } else {
            button.setEnabled(true);
        }
    }
}

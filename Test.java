package com.example.karthikeyan.karthikphplogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Test extends AppCompatActivity {

    private long COUNTDOWN_IN_MILLIS;
    private long l;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    HttpURLConnection httpURLConnection = null;
    HttpURLConnection httpURLConnection1 = null;
    HttpURLConnection httpURLConnection2 = null;
    HttpURLConnection httpURLConnection3 = null;
    HttpURLConnection httpURLConnection4 = null;
    HttpURLConnection httpURLConnectionTime = null;
    InputStream is = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    InputStream is1 = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    InputStream is2 = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    InputStream is3 = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    InputStream is4 = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    InputStream isTime = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    private TextView Countdown;
    private TextView quizQuestion;
    private RadioGroup radioGroup;
    private RadioButton optionOne;
    private RadioButton optionTwo;
    private RadioButton optionThree;
    private RadioButton optionFour;
    private int currentQuizQuestion;
    private int quizCount;
    private QuizWrapper firstQuestion;
    private List<QuizWrapper> parsedObject;
    private String line;
    private String line1;
    private String lineTime;
    private String resultTime = "";
    private String result = "";
    private String chosenOption;
    private String Questions[];
    private String Option1[];
    private String Option2[];
    private String Option3[];
    private String Option4[];
    private String QId[];
    private String TestTime[];
    private String CorrectAnswer[];
    private String optionChosen;
    private String optionchosen;
    private String TId;
    private float num;
    private String CId;
    private String RollNo;
    private int NoOfQuestions;
    private String batch;
    JSONArray JA;
    JSONArray JATime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        quizQuestion = (TextView) findViewById(R.id.tvQuestion);
        quizQuestion.setMovementMethod(new ScrollingMovementMethod());
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        optionOne = (RadioButton) findViewById(R.id.radio1);
        optionTwo = (RadioButton) findViewById(R.id.radio2);
        optionThree = (RadioButton) findViewById(R.id.radio3);
        optionFour = (RadioButton) findViewById(R.id.radio4);
        Countdown = (TextView)findViewById(R.id.tvCount);
        final Button previousButton = (Button) findViewById(R.id.btnPrev);
        final Button nextButton = (Button) findViewById(R.id.btnNext);
        final Button submitButton = (Button) findViewById(R.id.btnSubmit);
        submitButton.setEnabled(false);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        RollNo = extras.getString("RollNo");
        String s = extras.getString("Course");
        batch = extras.getString("Batch");
        CId = s;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("http://192.168.43.235/QuizApp/QuestionRetrieve.php?String1=" + s + "&String2=" + RollNo +"&String3=" + batch );
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
            //System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        System.out.println("Before JA");
            try {
                System.out.println("Before jsonarray");
                System.out.println(result);
                JA = new JSONArray(result);
                JSONObject jsonObject = null;
                System.out.println("After jsonarray");
                 System.out.println(result);
                System.out.println(JA.length());
                Questions = new String[JA.length()];
                Option1 = new String[JA.length()];
                Option2 = new String[JA.length()];
                Option3 = new String[JA.length()];
                Option4 = new String[JA.length()];
                QId = new String[JA.length()];
                CorrectAnswer = new String[JA.length()];
                for (int i = 0; i < JA.length(); i++) {
                    jsonObject = JA.getJSONObject(i);
                    Questions[i] = jsonObject.getString("QStatement");
                    Option1[i] = jsonObject.getString("Option1");
                    Option2[i] = jsonObject.getString("Option2");
                    Option3[i] = jsonObject.getString("Option3");
                    Option4[i] = jsonObject.getString("Option4");
                    QId[i] = jsonObject.getString("QId");
                    CorrectAnswer[i] = jsonObject.getString("Answer");
                    TId = jsonObject.getString("TId");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int index;
            String temp;
        Random random = new Random();
        for (int i = Questions.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
                temp = Questions[index];
                Questions[index] = Questions[i];
                Questions[i] = temp;
                temp = Option1[index];
                Option1[index] = Option1[i];
                Option1[i] = temp;
                temp = Option2[index];
                Option2[index] = Option2[i];
                Option2[i] = temp;
                temp = Option3[index];
                Option3[index] = Option3[i];
                Option3[i] = temp;
                temp = Option4[index];
                Option4[index] = Option4[i];
                Option4[i] = temp;
                temp = QId[index];
                QId[index] = QId[i];
                QId[i] = temp;
                temp = CorrectAnswer[index];
                CorrectAnswer[index] = CorrectAnswer[i];
                CorrectAnswer[i] = temp;
        }
        String[] OptionsRandom = new String[4];
        int []indice = new int[4];
        for(int i=0;i<4;i++){
            indice[i]=i;
        }
        Random random1 = new Random();
        for(int j=0;j<Questions.length;j++){
            OptionsRandom[0]=Option1[j];
            OptionsRandom[1]=Option2[j];
            OptionsRandom[2]=Option3[j];
            OptionsRandom[3]=Option4[j];
            for(int i = 3;i>=0;i--){
                int indexs = random1.nextInt(i + 1);
                // Simple swap
                int a = indice[indexs];
                indice[indexs] = indice[i];
                indice[i] = a;
            }
            Option1[j]=OptionsRandom[indice[0]];
            Option2[j]=OptionsRandom[indice[1]];
            Option3[j]=OptionsRandom[indice[2]];
            Option4[j]=OptionsRandom[indice[3]];
        }
            int i = 0;
            System.out.println(TId);

            try {
                URL url = new URL("http://192.168.43.235/QuizApp/TestTimeRetrieve.php?String1="+TId);
                httpURLConnectionTime = (HttpURLConnection) url.openConnection();
                httpURLConnectionTime.connect();
                isTime = httpURLConnectionTime.getInputStream();
            } catch (MalformedURLException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(isTime, "iso-8859-1"), 8);
                StringBuilder stringBuilder = new StringBuilder();
                while ((lineTime = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lineTime + '\n');
                }
                isTime.close();
                resultTime = stringBuilder.toString();
                //System.out.println(result);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(resultTime);
            System.out.println("Before JATime");
            try {
                System.out.println("Before jsonarrayTime");
                System.out.println(resultTime);
                JATime = new JSONArray(resultTime);
                JSONObject jsonObject = null;
                System.out.println("After jsonarrayTime");
                // System.out.println(resultTime);
                //System.out.println(JATime.length());
                TestTime = new String[JATime.length()];
                for (i = 0; i < JATime.length(); i++) {
                    jsonObject = JATime.getJSONObject(i);
                    TestTime[i] = jsonObject.getString("TDuration");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(TestTime[0]);
            l = Long.parseLong(TestTime[0]);
            COUNTDOWN_IN_MILLIS = l*60*1000;

            quizQuestion.setText(Questions[0]);
            optionOne.setText(Option1[0]);
            optionTwo.setText(Option2[0]);
            optionThree.setText(Option3[0]);
            optionFour.setText(Option4[0]);
            NoOfQuestions = JA.length();
            previousButton.setEnabled(false);
            for (int j = 0; j < JA.length(); j++) {
                try {
                    System.out.println(QId[j]);
                    URL url1 = new URL("http://192.168.43.235/QuizApp/emptyResponse.php?String1=" + RollNo + "&String2=" + QId[j]);
                    httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                    httpURLConnection1.connect();
                    is1 = httpURLConnection1.getInputStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        try {
            URL url = new URL("http://192.168.43.235/QuizApp/QuestionRetrieve.php?String1=" + s + "&String2=" + RollNo);
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
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            currentQuizQuestion = 0;
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
            previousButton.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    nextButton.setEnabled(true);
                    submitButton.setEnabled(false);
                    System.out.println("Previous button pressed");
                    currentQuizQuestion--;
                    System.out.println("Previous button pressed"+currentQuizQuestion);

                    if (currentQuizQuestion < 0) {

                        System.out.println("No previous question");
                        previousButton.setEnabled(false);

                    } else {
                        quizQuestion.setText(Questions[currentQuizQuestion]);
                        optionOne.setText(Option1[currentQuizQuestion]);
                        optionTwo.setText(Option2[currentQuizQuestion]);
                        optionThree.setText(Option3[currentQuizQuestion]);
                        optionFour.setText(Option4[currentQuizQuestion]);
                        try {
                            URL url4 = new URL("http://192.168.43.235/QuizApp/AnswerRetrieve.php?String1=" + RollNo + "&String2=" + QId[currentQuizQuestion]);
                            httpURLConnection4 = (HttpURLConnection) url4.openConnection();
                            httpURLConnection4.connect();
                            is4 = httpURLConnection4.getInputStream();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is4, "iso-8859-1"), 8);
                            StringBuilder stringBuilder = new StringBuilder();
                            while ((line1 = bufferedReader.readLine()) != null) {
                                stringBuilder.append(line1 + '\n');
                            }
                            is4.close();
                            chosenOption = stringBuilder.toString();
                            System.out.println(chosenOption);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONArray JA = new JSONArray(chosenOption);
                            JSONObject jsonObject = JA.getJSONObject(0);
                            optionChosen = jsonObject.getString("Response");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                            radioGroup.clearCheck();
                            System.out.println(optionChosen);
                            if (Option1[currentQuizQuestion].equals(optionChosen)) {
                                System.out.println("Option1 matches");
                                optionOne.setChecked(true);
                            } else if (Option2[currentQuizQuestion].equals(optionChosen)) {
                                System.out.println("Option2 matches");
                                optionTwo.setChecked(true);
                            } else if (Option3[currentQuizQuestion].equals(optionChosen)) {
                                System.out.println("Option3 matches");
                                optionThree.setChecked(true);
                            } else if (Option4[currentQuizQuestion].equals(optionChosen)) {
                                System.out.println("Option4 matches");
                                optionFour.setChecked(true);
                            }
                    }
                }
            });
            nextButton.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    System.out.println("Next button pressed");
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    System.out.println(selectedId);
                    if (selectedId > -1) {
                        RadioButton radioButton = (RadioButton) findViewById(selectedId);
                        String response = radioButton.getText().toString();
                        System.out.println(response);
                        URL url3 = null;
                        if(currentQuizQuestion >= 0){
                            try {
                                String updateURL = "http://192.168.43.235/QuizApp/updateResponse.php?String1=" + RollNo + "&String2=" + QId[currentQuizQuestion] + "&String3=" + response;
                                String URLUpdate = updateURL.replace(" ","%20");
                                url3 = new URL(URLUpdate);
                                System.out.println(url3);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            try {
                                assert url3 != null;
                                httpURLConnection3 = (HttpURLConnection) url3.openConnection();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                httpURLConnection3.connect();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                is3 = httpURLConnection3.getInputStream();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    currentQuizQuestion++;
                    System.out.println("Next button pressed"+currentQuizQuestion);
                    previousButton.setEnabled(true);
                    if (currentQuizQuestion >= JA.length()) {

                        System.out.println("No next question");
                        nextButton.setEnabled(false);
                        submitButton.setEnabled(true);

                    } else {
                        quizQuestion.setText(Questions[currentQuizQuestion]);
                        optionOne.setText(Option1[currentQuizQuestion]);
                        optionTwo.setText(Option2[currentQuizQuestion]);
                        optionThree.setText(Option3[currentQuizQuestion]);
                        optionFour.setText(Option4[currentQuizQuestion]);
                        try {
                            URL url4 = new URL("http://192.168.43.235/QuizApp/AnswerRetrieve.php?String1=" + RollNo + "&String2=" + QId[currentQuizQuestion]);
                            httpURLConnection4 = (HttpURLConnection) url4.openConnection();
                            httpURLConnection4.connect();
                            is4 = httpURLConnection4.getInputStream();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is4, "iso-8859-1"), 8);
                            StringBuilder stringBuilder = new StringBuilder();
                            while ((line1 = bufferedReader.readLine()) != null) {
                                stringBuilder.append(line1 + '\n');
                            }
                            is4.close();
                            chosenOption = stringBuilder.toString();
                            System.out.println(chosenOption);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONArray JA = new JSONArray(chosenOption);
                            JSONObject jsonObject = JA.getJSONObject(0);
                            optionChosen = jsonObject.getString("Response");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        radioGroup.clearCheck();
                        System.out.println("Reached .equals");
                        System.out.println(optionChosen);
                        if (Option1[currentQuizQuestion].equals(optionChosen)) {
                            System.out.println("Option1 matches");
                            optionOne.setChecked(true);
                        } else if (Option2[currentQuizQuestion].equals(optionChosen)) {
                            System.out.println("Option2 matches");
                            optionTwo.setChecked(true);
                        } else if (Option3[currentQuizQuestion].equals(optionChosen)) {
                            System.out.println("Option3 matches");
                            optionThree.setChecked(true);
                        } else if (Option4[currentQuizQuestion].equals(optionChosen)) {
                            System.out.println("Option4 matches");
                            optionFour.setChecked(true);
                        }
                    }
                }
            });
            i = currentQuizQuestion;
            submitButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(Test.this, TestScore.class);
                    Bundle extras = new Bundle();
                    extras.putString("RollNo", RollNo);
                    extras.putString("TId", TId);
                    extras.putString("CId", CId);
                    extras.putInt("No", NoOfQuestions);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
        }

    @Override
    public void onBackPressed() {
        (new AlertDialog.Builder(this))
                .setTitle("Confirm action")
                .setMessage("Do you want to submit the test?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Test.this, TestScore.class);
                        Bundle extras = new Bundle();
                        extras.putString("RollNo", RollNo);
                        extras.putString("TId", TId);
                        extras.putString("CId", CId);
                        extras.putInt("No", NoOfQuestions);
                        intent.putExtras(extras);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                Intent intent = new Intent(Test.this, TestScore.class);
                Bundle extras = new Bundle();
                extras.putString("RollNo", RollNo);
                extras.putString("TId", TId);
                extras.putString("CId", CId);
                extras.putInt("No", NoOfQuestions);
                intent.putExtras(extras);
                startActivity(intent);

            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int)(timeLeftInMillis/1000)/60;
        int seconds = (int)(timeLeftInMillis/1000)%60;
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        Countdown.setText(timeFormatted);
        if(timeLeftInMillis < 10000){
            Countdown.setTextColor(Color.RED);
        }
    }
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}


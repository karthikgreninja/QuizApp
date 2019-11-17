package com.example.karthikeyan.karthikphplogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String>   {
    private AlertDialog alertDialog;
    private Context ctx;
    private String login_roll;
    private String login_pass;
    private String type;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information......");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.43.235/QuizApp/register.php";
        String login_url = "http://192.168.43.235/QuizApp/login.php";
        String prof_url = "http://192.168.43.235/QuizApp/Login_prof.php";
        String admin_url = "http://192.168.43.235/QuizApp/Login_admin.php";
        String method = params[0];
        if (method.equals("register")) {
            String rollno = params[2];
            String pass = params[3];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("roll", "UTF-8") + "=" + URLEncoder.encode(rollno, "UTF-8") + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration completed successfully";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")) {
            login_roll = params[2];
            login_pass = params[3];
            type = params[1];
            try {
                URL url;
                if(type.equals("Student")) {
                    url = new URL(login_url);
                }
                else if(type.equals("Professor")){
                    url = new URL(prof_url);
                }
                else{
                    url = new URL(admin_url);
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("login_roll", "UTF-8") + "=" + URLEncoder.encode(login_roll, "UTF-8") + "&" + URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result == null){
            alertDialog.setMessage("Please check your server connection");
            alertDialog.show();
        }
        else if (result.equals("Registration completed successfully")) {
            Toast.makeText(ctx, "result", Toast.LENGTH_LONG).show();
        } else {
            alertDialog.setMessage(result);
            alertDialog.show();
            if (result.contains("Login success")) {
                if(result.contains("professor")){
                    Intent DisplayActivity = new Intent(this.getActivity(),ProfMenu.class);
                    DisplayActivity.putExtra("arg",login_roll);
                    ctx.startActivity(DisplayActivity);
                }
                else if(result.contains("Admin")){
                    Intent Admin = new Intent(this.getActivity(), AdminActivity.class);
                    Admin.putExtra("arg", login_roll);
                    ctx.startActivity(Admin);
                }
                else {
                    Intent QuizActivity = new Intent(this.getActivity(), StudentMenu.class);
                    QuizActivity.putExtra("arg", login_roll);
                    ctx.startActivity(QuizActivity);
                }
            }

        }
    }

    private Context getActivity() {
        return ctx;
    }
}

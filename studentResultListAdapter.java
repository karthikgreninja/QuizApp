package com.example.karthikeyan.karthikphplogin;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class studentResultListAdapter extends ArrayAdapter<studentQuizResult> {
    private Context mContext;
    private int mResource;
    private TextView Test;
    private TextView Score;
    private String test;
    private String score;

    public studentResultListAdapter(Context context, int resource, ArrayList<studentQuizResult> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        test = Objects.requireNonNull(getItem(position)).getTest();
        System.out.println(test);
        float Scored = Objects.requireNonNull(getItem(position)).getScore();
        System.out.println(score);
        score = Float.toString(Scored);
        StudentResult studentResult = new StudentResult(test, Scored);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        Test = (TextView) convertView.findViewById(R.id.textViewRoll);
        Score = (TextView) convertView.findViewById(R.id.textViewScore);
        Test.setText(test);
        Score.setText(score);
        return convertView;
    }
}

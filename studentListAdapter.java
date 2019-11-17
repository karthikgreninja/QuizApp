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

public class studentListAdapter extends ArrayAdapter<StudentResult> {

    private Context mContext;
    private int mResource;
    TextView Roll;
    TextView Score;
    String roll;
    String score;

    public studentListAdapter(Context context, int resource, ArrayList<StudentResult> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        roll = Objects.requireNonNull(getItem(position)).getRoll();
        System.out.println(roll);
        float Scored = Objects.requireNonNull(getItem(position)).getScore();
        System.out.println(score);
        score = Float.toString(Scored);
        StudentResult studentResult = new StudentResult(roll, Scored);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        Roll = (TextView) convertView.findViewById(R.id.textViewRoll1);
        Score = (TextView) convertView.findViewById(R.id.textViewScore1);
        Roll.setText(roll);
        Score.setText(score);
        return convertView;
    }

}

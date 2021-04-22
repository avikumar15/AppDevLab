package com.example.colourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.colourgame.MainActivity.SP_KEY;
import static com.example.colourgame.MainActivity.one_key;
import static com.example.colourgame.MainActivity.three_key;
import static com.example.colourgame.MainActivity.two_key;

public class ScoreActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    SharedPreferences sharedPref;
    ArrayList<Integer> scores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        sharedPref = getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);

        scores.add(sharedPref.getInt(one_key,-1));
        scores.add(sharedPref.getInt(two_key,-1));
        scores.add(sharedPref.getInt(three_key,-1));

        Collections.sort(scores, Collections.<Integer>reverseOrder());

        tv1 =findViewById(R.id.tv1);
        tv2 =findViewById(R.id.tv2);
        tv3 =findViewById(R.id.tv3);

        List<String> res = new ArrayList<>();

        for(int i=0; i<scores.size(); i++) {
            if(scores.get(i)==-1)
                res.add("Score "+(i+1)+": NOT FOUND");
            else
                res.add("Score "+(i+1)+": "+scores.get(i)+"");
        }

        tv1.setText(res.get(0));
        tv2.setText(res.get(1));
        tv3.setText(res.get(2));

    }
}
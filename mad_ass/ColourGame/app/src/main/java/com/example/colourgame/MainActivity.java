package com.example.colourgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String HIGH_SCORE = "HIGH_SCORE";
    static final String SP_NAME = "SP_NAME";

    List<Integer> r = new ArrayList<>(), g = new ArrayList<>(), b = new ArrayList<>();

    TextView tvStreak;

    TextView qn1;
    List<View> qn1o = new ArrayList<>();

    View qn2;
    List<TextView> qn2o = new ArrayList<>();

    TextView tvVerdict;

    ConstraintLayout clCodeToCol, clColToCode;

    Integer mode = -1;
    int correct;

    int score=0;
    int chosen=-1;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRandomColourValues();
        bindViews();
        setUpViewsBasedOnMode();
    }

    private void setUpViewsBasedOnMode() {
        mode = getIntent().getIntExtra(StartActivity.MODE, -1);
        if(mode==-1) {
            Toast.makeText(this, "Something unexpected occured!", Toast.LENGTH_SHORT).show();
        } else {
            if(mode==1) {
                clCodeToCol.setVisibility(View.VISIBLE);
                qn1.setText("RGB("+r.get(correct)+","+g.get(correct)+","+b.get(correct)+")");
                for(int i=0; i<3; i++) {
                    qn1o.get(i).setBackgroundColor(Color.rgb(r.get(i), g.get(i), b.get(i)));
                    qn1o.get(i).setOnClickListener(this);
                }
            } else {
                clColToCode.setVisibility(View.VISIBLE);
                qn2.setBackgroundColor(Color.rgb(r.get(correct), g.get(correct), b.get(correct)));
                for(int i=0; i<3; i++) {
                    qn2o.get(i).setText("RGB("+r.get(i)+","+g.get(i)+","+b.get(i)+")");
                    qn2o.get(i).setOnClickListener(this);
                }
            }
        }
    }

    private void bindViews() {
        tvStreak = findViewById(R.id.tvStreak);
        tvVerdict = findViewById(R.id.tvResult2);
        clCodeToCol = findViewById(R.id.cl_code_to_col);
        clColToCode = findViewById(R.id.cl_col_to_code);
        clCodeToCol.setVisibility(View.GONE);
        clColToCode.setVisibility(View.GONE);
        tvVerdict.setVisibility(View.GONE);

        qn1 = findViewById(R.id.tvQn1);
        qn1o.add(findViewById(R.id.option1View));
        qn1o.add(findViewById(R.id.option2View));
        qn1o.add(findViewById(R.id.option3View));

        qn2 = findViewById(R.id.tvQn2);
        qn2o.add((TextView)findViewById(R.id.option1View2));
        qn2o.add((TextView)findViewById(R.id.option2View2));
        qn2o.add((TextView)findViewById(R.id.option3View2));
    }

    private void getRandomColourValues() {
        r = getRandomNumbers();
        g = getRandomNumbers();
        b = getRandomNumbers();

        correct = (new Random().nextInt(3));
    }

    private ArrayList<Integer> getRandomNumbers() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> finalList = new ArrayList<>();
        for (int i=1; i<=256; i++) {
            list.add(Integer.valueOf(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) {
            finalList.add(list.get(i));
        }
        return finalList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.option1View:
                chosen=0;
            case R.id.option1View2:
                chosen=0;
                break;
            case R.id.option2View:
                chosen=1;
            case R.id.option2View2:
                chosen=1;
                break;
            case R.id.option3View:
                chosen=2;
            case R.id.option3View2:
                chosen=2;
                break;
        }

        if(chosen==correct) {
            Toast.makeText(this, "Correct Answer. Try next one!", Toast.LENGTH_SHORT).show();
            score++;
        } else {

            sharedPref = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            int currentHigh = sharedPref.getInt(MainActivity.HIGH_SCORE, 0);

            if(score>currentHigh) {
                editor.putInt(HIGH_SCORE, score);
                editor.apply();
            }

            Toast.makeText(this, "Oops, this isn't the correct answer. Restarting", Toast.LENGTH_SHORT).show();
            score=0;
        }
        tvStreak.setText("Current Streak: "+score);
        getRandomColourValues();
        setUpViewsBasedOnMode();
    }
}
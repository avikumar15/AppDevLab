package com.example.colourgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    float dX;
    float dY;
    int lastAction;

    ArrayList<TextView> tvs = new ArrayList<>();
    ArrayList<View> views = new ArrayList<>();
    ArrayList<Integer> anss = new ArrayList<>();
    HashMap<Integer,Integer> hashIdToNumber = new HashMap<>();
    ArrayList<Pair<Float,Float>> positions = new ArrayList<>();

    int selectedOption=-1;
    int correctAns=-1;

    Button btnVerify;
    TextView tvResult;

    int score=0;

    float currentX, currentY;

    public static final String INTENT_TEXT= "INTENT_TEXT";

    public static final String one_key = "one_key";
    public static final String two_key = "two_key";
    public static final String three_key = "three_key";
    public static final String pos_key = "pos_key";

    public static final String SP_KEY = "SP_KEY";

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setColor();

        for(int i=0; i<tvs.size(); i++)
            tvs.get(i).setOnTouchListener(this);


        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });

        sharedPref = getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);

    }

    public void startService(String input) {

        Intent serviceIntent = new Intent(this, ServiceClass.class);
        serviceIntent.putExtra(INTENT_TEXT, input);

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void check() {

        Log.i("MainActivity", "Selected: "+selectedOption+" and correctAns: "+correctAns);

        selectedOption = getSelectedOption(currentX, currentY);

        if(selectedOption==-1) {
            Toast.makeText(this, "Can't proceed, since none of the option selected!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(selectedOption==correctAns) {
            Toast.makeText(this, "+1", Toast.LENGTH_SHORT).show();
            score++;
            tvs.get(selectedOption).setBackgroundColor(Color.rgb(255,255,255));
            views.get(correctAns).setBackgroundColor(Color.rgb(255,255,255));

            startService("Right Answer, Current Score: "+score);

        } else {
            Toast.makeText(this, "Game over", Toast.LENGTH_SHORT).show();

            startService("Game Over");

            SharedPreferences.Editor editor = sharedPref.edit();

            int on = sharedPref.getInt(one_key, -1);
            int tw = sharedPref.getInt(two_key, -1);
            int th = sharedPref.getInt(three_key, -1);

            int ps = sharedPref.getInt(pos_key, -1);

            if(on==-1) {
                editor.putInt(one_key, score);
                editor.putInt(two_key, tw);
                editor.putInt(three_key, th);
                editor.putInt(pos_key, ps);
            } else if(tw==-1) {
                editor.putInt(one_key, on);
                editor.putInt(two_key, score);
                editor.putInt(three_key, th);
                editor.putInt(pos_key, ps);
            } else if(th==-1) {
                editor.putInt(one_key, on);
                editor.putInt(two_key, tw);
                editor.putInt(three_key, score);
                editor.putInt(pos_key, ps);
            } else {
                if(ps==-1 || ps==0) {
                    editor.putInt(one_key, score);
                    editor.putInt(pos_key, 1);
                } else if(ps==1) {
                    editor.putInt(two_key, score);
                    editor.putInt(pos_key, 2);
                } else {
                    editor.putInt(three_key, score);
                    editor.putInt(pos_key, 0);
                }
            }

            editor.apply();

            score=0;

            Intent intent = getIntent();
            finish();
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        tvResult.setText("SCORE: "+score);
    }

    private void setColor() {
        Integer[] arr = new Integer[256];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        Collections.shuffle(Arrays.asList(arr));

        Integer[] arr2 = new Integer[6];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i;
        }

        Collections.shuffle(Arrays.asList(arr2));

        int crnt=0;

        for(int i=0; i<tvs.size(); i++) {
            views.get(arr2[i]).setBackgroundColor(Color.rgb(arr[crnt], arr[crnt+1], arr[crnt+2]));
            tvs.get(i).setText("("+arr[crnt]+","+arr[crnt+1]+","+arr[crnt+2]+")");
            anss.add(arr2[i]);
            crnt += 3;
        }
    }

    private void bindViews() {

        tvs.add((TextView)findViewById(R.id.tvColor1));
        tvs.add((TextView)findViewById(R.id.tvColor2));
        tvs.add((TextView)findViewById(R.id.tvColor3));
        tvs.add((TextView)findViewById(R.id.tvColor4));
        tvs.add((TextView)findViewById(R.id.tvColor5));
        tvs.add((TextView)findViewById(R.id.tvColor6));

        hashIdToNumber.put(R.id.tvColor1,0);
        hashIdToNumber.put(R.id.tvColor2,1);
        hashIdToNumber.put(R.id.tvColor3,2);
        hashIdToNumber.put(R.id.tvColor4,3);
        hashIdToNumber.put(R.id.tvColor5,4);
        hashIdToNumber.put(R.id.tvColor6,5);

        views.add((View)findViewById(R.id.ansColor1));
        views.add((View)findViewById(R.id.ansColor2));
        views.add((View)findViewById(R.id.ansColor3));
        views.add((View)findViewById(R.id.ansColor4));
        views.add((View)findViewById(R.id.ansColor5));
        views.add((View)findViewById(R.id.ansColor6));

        btnVerify = findViewById(R.id.btnVerify);
        tvResult = findViewById(R.id.tvScore);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        correctAns = hashIdToNumber.get(view.getId());
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                correctAns = hashIdToNumber.get(view.getId());
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                view.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;
                currentX = event.getRawX() + dX;
                currentY = event.getRawY() + dY;

                if(getSelectedOption(currentX, currentY) != -1) {
                    view.setAlpha(0.5f);
                    Log.i("MainActivity", "Setting alpha as 0.5");
                } else {
                    view.setAlpha(1f);
                }

                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                return false;
        }
        return true;
    }

    private int getSelectedOption(float x, float y) {

        if(positions.size()==0)
            for(int i=0; i<views.size(); i++)
                positions.add(new Pair<>(views.get(i).getX(), views.get(i).getY()));

        Log.i("MainActivity", "x: "+x+" y: "+y);
        for(int i=0; i<positions.size(); i++) {
            Pair<Float, Float> fpair = positions.get(i);
            Log.i("MainActivity", "x in pair: "+fpair.first+" y in pair: "+fpair.second);
            if(x<100f+fpair.first && x>fpair.first-100f && y<fpair.second+100f && y>fpair.second-150) {
                Log.i("MainActivity", "Yes "+i);
                return i;
            }
        }

        return -1;
    }
}
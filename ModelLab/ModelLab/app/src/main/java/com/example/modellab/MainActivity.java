package com.example.modellab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import java.util.ArrayList;

import static com.example.modellab.Utils.createToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AppInterface {

    int choice = -1;

    private static final String TAG = "MAINACTIVITY";
    int data = 2;

    EditText etEventName, etTime;
    FragmentContainerView fcv;
    MainFragment mf;

    String nameOfUser = "";

    TextView tv;

    SharedPreferences sharedPref;

    ArrayList<Intent> intents = new ArrayList<>();

    String movieId = "sGRsXdcZeVo";
    String movieUri = "https://play.google.com/store/movies/details?id="+movieId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameOfUser = getIntent().getStringExtra(Utils.NAME);

        bindViews();
        transact();
        add();

        if(savedInstanceState!=null) {
            choice = savedInstanceState.getInt("current");
        }

        sharedPref = getSharedPreferences(Utils.SP_KEY, Context.MODE_PRIVATE);

    }

    private void add() {
        intents.add(new Intent("android.intent.action.MUSIC_PLAYER"));
        intents.add(new Intent(Intent.ACTION_VIEW, Uri.parse(movieUri)));
        intents.add(new Intent(AlarmClock.ACTION_SET_ALARM));
    }

    private void transact() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fcv_main_activity, mf)
                .commit();
    }

    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindViews() {
        etEventName = findViewById(R.id.etEventName);
        etTime = findViewById(R.id.etTime);
        fcv = findViewById(R.id.fcv_main_activity);
        mf = new MainFragment();
        mf.setAppInterface((AppInterface)this);
        tv = findViewById(R.id.tvWelcomeMain);

        tv.setText("WELCOME "+nameOfUser+"!!");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("current", choice);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onClickedButton(int num) {
        choice = num;

        switch (choice) {
            case -1:
                createToast(this, "No Option Selected");
                break;
            case 1:
                createToast(this, "Music!");
                break;
            case 2:
                createToast(this, "Movies");
                break;
            case 3:
                createToast(this, "Clock");
                break;
        }

        if(choice==-1) {
            return;
        } else {
            mf.removeOnClick();

            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intents.get(choice-1));
                }
            }, 5000);

        }
    }
}
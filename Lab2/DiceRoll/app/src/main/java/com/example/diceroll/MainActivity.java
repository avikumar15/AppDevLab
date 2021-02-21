package com.example.diceroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements DiceInterface {

    DiceView dice;
    Button generate;

    TextView tvScoreboard;
    TextView tvValue;
    TextView tvPlayerTurn;
    Vibrator vib;

    int moveCnt = 0;

    ArrayList<String> players = new ArrayList<>();
    ArrayList<Integer> playerScore = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateViews();
        initialValues();
    }

    @SuppressLint("SetTextI18n")
    private void initialValues() {
        players.add(getIntent().getStringExtra(StartActivity.Name1));
        players.add(getIntent().getStringExtra(StartActivity.Name2));

        playerScore.add(0);
        playerScore.add(0);

        tvPlayerTurn.setText(players.get(moveCnt % 2) + "'s Turn");

        tvScoreboard.setText(players.get(0) + " " + playerScore.get(0) + " | " +
                playerScore.get(1) + " " + players.get(1));

        dice.setInterface(this);
    }

    private void instantiateViews() {
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        dice = findViewById(R.id.dv);
        generate = findViewById(R.id.btn_generate);

        tvScoreboard = findViewById(R.id.tvScoreboard);
        tvValue = findViewById(R.id.tvValue);
        tvPlayerTurn = findViewById(R.id.tvPlayerTurn);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });
    }

    private void rollDice() {

        vib.vibrate(1000);

        tvValue.setText("");

        int rnd1 = new Random().nextInt(3);

        // rnd1 in range 2, 3, 4
        rnd1 += 2;

        // rnd2 in range 0, 1, 2, 3, 4, 5
        int rnd2 = new Random().nextInt(6);

        dice.setRequirements(rnd1, rnd2);

        // Starts rolling by setting flag to true
        dice.startShaking();

        // disable onclick for this duration
        generate.setOnClickListener(null);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRolled(int value) {

        moveCnt++;
        tvValue.setText((value + 1) + "");

        tvPlayerTurn.setText(players.get(moveCnt % 2) + "'s Turn");
        playerScore.set((moveCnt + 1) % 2, playerScore.get((moveCnt + 1) % 2) + value + 1);

        tvScoreboard.setText(players.get(0) + " " + playerScore.get(0) + " | " +
                playerScore.get(1) + " " + players.get(1));

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });

        checkWinner(moveCnt + 1);

    }

    @SuppressLint("SetTextI18n")
    private void checkWinner(int playerIndex) {
        playerIndex %= 2;
        if (playerScore.get(playerIndex) >= 25) {
            generate.setOnClickListener(null);
            Toast.makeText(this, "Winner is " + players.get(playerIndex) + " and loser is " + players.get((playerIndex + 1) % 2),
                    Toast.LENGTH_SHORT).show();
            generate.setText("Can't click me now :P");
        }
    }

}

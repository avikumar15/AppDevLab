package com.example.tictactoesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int[][] board = new int[3][3];
    ArrayList<Button> btns = new ArrayList<>();
    ArrayList<String> chances = new ArrayList<>();
    ArrayList<String> players = new ArrayList<>();
    ArrayList<Integer> playerColor = new ArrayList<Integer>();

    TextView tvPlayerMove;

    int numberOfButtons = 9;
    int currentChance = 0;
    String firstPlayer = "First Player";
    String secondPlayer = "Second Player";

    String TAG = "PlayerMove";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            currentChance = savedInstanceState.getInt("current");
            board = (int[][]) savedInstanceState.getSerializable("board");

            setBoard();
        }
        else {
            firstPlayer = getIntent().getStringExtra(StartActivity.Name1);
            secondPlayer = getIntent().getStringExtra(StartActivity.Name2);

            instantiateVariables();
            setLayoutWithListeners();
        }
    }

    private void setBoard() {

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                int p = i*3+j;
                if(board[i][j]==-1) {

                } else {
                    btns.get(p).setBackgroundColor(playerColor.get(board[i][j]));
                }

            }
        }
    }


    private void instantiateVariables() {
        chances.add("X");
        chances.add("O");

        players.add(firstPlayer);
        players.add(secondPlayer);

        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                board[i][j]=-1;

        // First player is red and second is blue.
        playerColor.add(Color.parseColor("#FF6347"));
        playerColor.add(Color.parseColor("#6495ED"));

    }

    private void setLayoutWithListeners() {
        btns.add((Button) findViewById(R.id.tt00));
        btns.add((Button) findViewById(R.id.tt01));
        btns.add((Button) findViewById(R.id.tt02));
        btns.add((Button) findViewById(R.id.tt10));
        btns.add((Button) findViewById(R.id.tt11));
        btns.add((Button) findViewById(R.id.tt12));
        btns.add((Button) findViewById(R.id.tt20));
        btns.add((Button) findViewById(R.id.tt21));
        btns.add((Button) findViewById(R.id.tt22));

        tvPlayerMove = findViewById(R.id.textViewPlayer);

        for(int i=0; i<numberOfButtons; i++) {
            btns.get(i).setOnClickListener(this);
        }

        tvPlayerMove.setText(players.get(currentChance%2)+"'s move");
    }

    @Override
    public void onClick(View view) {
        String chance = chances.get((currentChance)%2);

        switch(view.getId()) {


            default:
                if(((Button)view).getText()=="O" || ((Button)view).getText()=="X") {
                    Toast.makeText(this,"Can't move here since already contains 'X' or 'O'", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    ((Button)view).setText(chance);
                    tvPlayerMove.setText(players.get(currentChance%2)+"'s move");

                    String idName = getResources().getResourceEntryName(view.getId());
                    // idname will be like ttij

                    board[Integer.parseInt(idName.charAt(2)+"")][Integer.parseInt(idName.charAt(3)+"")] = currentChance%2;

                    view.setBackgroundColor(playerColor.get((currentChance)%2));

                    if(checkIfWon(Integer.parseInt(idName.charAt(2)+""), Integer.parseInt(idName.charAt(3)+""))){
                        Toast.makeText(this,"Won by "+players.get(currentChance%2),Toast.LENGTH_SHORT).show();
                        tvPlayerMove.setText("Won by " + players.get(currentChance%2));
                        removeOnclickListeners();
                    } else if(isDrawn()) {
                        tvPlayerMove.setText("Game Drawn");
                        Toast.makeText(this,"Drawn",Toast.LENGTH_SHORT).show();
                        removeOnclickListeners();
                    } else {
                            currentChance++;
                            tvPlayerMove.setText(players.get(currentChance%2)+"'s move");
                    }


                }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("board", board);
        outState.putInt("current", currentChance);

    }

    private void removeOnclickListeners() {
        for(int i=0; i<numberOfButtons; i++)
            btns.get(i).setOnClickListener(null);
    }

    private Boolean checkIfWon(int posX, int posY) {

        int playersNumber = currentChance%2;
        Log.i(TAG, "Move by "+playersNumber+" and moved at ("+posX+","+posY+").");

        // Horizontal and veritcal checks
        Boolean check1 = true;
        Boolean check2 = true;

        // Diagonal checks
        Boolean check3 = true;
        Boolean check4 = true;

        // Horizontal check
        for(int i=0; i<3; i++) {
            if(board[posX][i]!=playersNumber)
                check1 = false;
        }

        Log.i(TAG, "Check 1: "+check1);

        // Vertical check
        for(int i=0; i<3; i++) {
            if(board[i][posY]!=playersNumber)
                check2 = false;
        }

        Log.i(TAG, "Check 2: "+check2);

        // Case of move in principal diagonal
        for(int i=0; i<3; i++)
            if(board[i][i]!=playersNumber)
                check3 = false;

        Log.i(TAG, "Check 3: "+check3);

        // Case of other diagonal
        for(int i=0; i<3; i++)
            if(board[i][2-i]!=playersNumber)
                check4 = false;

        Log.i(TAG, "Check 4: "+check4);

        return (check1 || check2 || check3 || check4);

    }

    private Boolean isDrawn() {

        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(board[i][j]==-1)
                    return false;

        return true;
    }

}

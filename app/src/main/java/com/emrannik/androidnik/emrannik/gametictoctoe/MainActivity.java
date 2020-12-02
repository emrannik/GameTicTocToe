package com.emrannik.androidnik.emrannik.gametictoctoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 is x,1 is o
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int gamefull = 1;
    boolean gameActive = true;
    Button restart;
    TextView playTheGame, score;
    boolean tie = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restart = findViewById(R.id.button);
        playTheGame = findViewById(R.id.textView2);
        score = findViewById(R.id.textView);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridLayout mlayout = findViewById(R.id.gridlayout);

                for (int i = 0; i < mlayout.getChildCount(); i++) {
                    ImageView child = (ImageView) mlayout.getChildAt(i);
                    child.setImageDrawable(null);
                }
                for (int i = 0; i < 9; i++) {
                    gameState[i] = 2;
                }
                gameActive = true;
                activePlayer = 0;
                playTheGame.setVisibility(View.VISIBLE);
                restart.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void dropIn(View view) {

        Log.e("MainActivity", "dropIn: started");
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.tictcx);
                activePlayer = 1;

            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.tictactoe_o);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(36000).setDuration(300);

            for (int i = 0; i < 8; i++) {
                if (
                        gameState[winningPositions[i][0]] == gameState[winningPositions[i][1]] &&
                                gameState[winningPositions[i][1]] == gameState[winningPositions[i][2]] &&
                                gameState[winningPositions[i][1]] != 2) {
                    gameActive = false;

                    String winner;
                    if (activePlayer == 1) {
                        winner = "Player 1";
                    } else {
                        winner = "Player 2";
                    }

                    Toast.makeText(this, winner + " Wins", Toast.LENGTH_SHORT).show();
                    playTheGame.setVisibility(View.GONE);
                    score.setVisibility(View.VISIBLE);
                    score.setText(winner + " Wins");
                    restart.setVisibility(View.VISIBLE);
                }

            }


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.mail:
                // do your code
                return true;
            case R.id.restarts:
                // do your code
                onRestart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}

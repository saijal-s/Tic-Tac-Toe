package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
        String player1;
        String player2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            player1=getIntent().getStringExtra("Player1");
            player2=getIntent().getStringExtra("Player2");
        }

        boolean gameActive = true;
        // Player representation
        // 0 - X
        // 1 - O
        int activePlayer = 0;
        int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

        // State meanings:
        //    0 - X
        //    1 - O
        //    2 - Null
        // put all win positions in a 2D array
        int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};
        public static int counter = 0;

        // this function will be called every time a
        // players tap in an empty box of the grid
        public void playerTap(View view) {
            ImageView img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());

            // game reset function will be called
            // if someone wins or the boxes are full
            if (!gameActive) {
                gameReset(view);
            }

            // if the tapped image is empty
            if (gameState[tappedImage] == 2) {
                // increase the counter
                // after every tap
                counter++;

                // check if its the last box
                if (counter == 9) {
                    // reset the game
                    gameActive = false;
                }

                // mark this position
                gameState[tappedImage] = activePlayer;

                // this will give a motion
                // effect to the image
                img.setTranslationY(-1000f);

                // change the active player
                // from 0 to 1 or 1 to 0
                if (activePlayer == 0) {
                    // set the image of x
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);

                    // change the status
                    status.setText(player2+"'s Turn - Tap to play");
                } else {
                    // set the image of o
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);

                    // change the status
                    status.setText(player1+"'s Turn - Tap to play");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
            int flag = 0;
            // Check if any player has won
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    flag = 1;

                    // Somebody has won! - Find out who!
                    String winnerStr;

                    // game reset function be called
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        Toast.makeText(MainActivity2.this,player1+" has won",Toast.LENGTH_SHORT).show();
                        winnerStr = "Click anywhere to start again";
                        counter=0;
                    }
                    else {
                        Toast.makeText(MainActivity2.this,player2+" has won",Toast.LENGTH_SHORT).show();
                        winnerStr = "Click on any to start again";
                        counter=0;
                    }
                    // Update the status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }
            }
            // set the status if the match draw
            if (counter == 9 && flag == 0) {
                TextView status = findViewById(R.id.status);
                Toast.makeText(MainActivity2.this,"Match Draw",Toast.LENGTH_SHORT).show();
                status.setText("Click on grid to start again");
                counter=0;
            }
        }

        // reset the game
        public void gameReset(View view) {
            gameActive = true;
            activePlayer = 0;
            for (int i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }
            // remove all the images from the boxes inside the grid
            ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

            TextView status = findViewById(R.id.status);
            status.setText(player1+"'s Turn - Tap to play");
        }

}
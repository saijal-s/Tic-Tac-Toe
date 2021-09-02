package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText p1;
    EditText p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1 = findViewById(R.id.player_1);
        p2 = findViewById(R.id.player_2);
    }

    public void nextPage(View view) {
        String player1 = p1.getText().toString();
        String player2 = p2.getText().toString();
        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
        intent.putExtra("Player1",player1);
        intent.putExtra("Player2",player2);
        startActivity(intent);
    }
}
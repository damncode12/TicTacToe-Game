package com.techyminati.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean isWinner=false;
    int imgClicked=-1;
    int player = 1; //player1 is cross
    int [][]winningStates={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int []gameState={-1,-1,-1,-1,-1,-1,-1,-1,-1};

    public void load(View view) {
            ImageView v = (ImageView) view;
            int tag = Integer.parseInt(v.getTag().toString());
            imgClicked=gameState[tag];
            if (!isWinner && imgClicked == -1) {
            if (player == 1) {
                v.setImageResource(R.drawable.cross);
                gameState[tag] = player;
                Toast.makeText(this, tag + "" + "Cross", Toast.LENGTH_SHORT).show();
                player = 0; //Zero player
            } else {
                v.setImageResource(R.drawable.zero);
                gameState[tag] = player;
                Toast.makeText(this, tag + "" + "Zero", Toast.LENGTH_SHORT).show();
                player = 1;
            }
                for (int[] winningState : winningStates) {
                    if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] > -1) {
                        Toast.makeText(this, "Winner is" + (player == 0 ? 1 : 0), Toast.LENGTH_SHORT).show();
                        isWinner = true;
                    }
                }
        }
    }

    public void reset(View view){
        androidx.gridlayout.widget.GridLayout gridLayout=findViewById(R.id.gridLayout);
        int totalImages = gridLayout.getChildCount();
        for (int i=0;i<totalImages;i++){
            ImageView v = (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
        }
        isWinner=false;
        imgClicked=-1;
        player=1;
        Arrays.fill(gameState, -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

package com.android.disneyquiz.disneyquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (ImageView) findViewById(R.id.playId);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaQuizActivity.class);
                startActivity(intent);
            }
        });
    }
    /*public void btnJogarOnClick(View v){
        Intent intent = new Intent(MainActivity.this, TelaQuizActivity.class);
        startActivity(intent);
    }*/
}

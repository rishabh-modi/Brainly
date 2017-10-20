package com.example.rishabh.brainly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView score = (TextView)findViewById(R.id.score);

        String s = getIntent().getStringExtra("score");

        score.setText(s);

    }
}

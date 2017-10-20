package com.example.rishabh.brainly;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button GoButton;
    Button playAgain;
    Button showScore;
    Button shareButton;
    TextView sumText;
    int locationOfAnswer;
    int incorrectAnswer;
    int score = 0;
    int scoreString;
    int totalQuestions =  0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView correctText;
    TextView scoreText;
    TextView timerText;
    int num1,num2;
    ArrayList<Integer>answer = new ArrayList<Integer>();
    RelativeLayout relativeLayout;

    public void start (View view)
    {

        GoButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void generateQuestion(View view)
    {
        Random random = new Random();

          num1 = random.nextInt(51);
          num2 = random.nextInt(51);

        sumText.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        locationOfAnswer = random.nextInt(4);
        answer.clear();

        for(int i = 0; i<4 ; i++)
        {
            if(i == locationOfAnswer)
            {
                answer.add(num1 + num2);
            }
            else
            {
                incorrectAnswer = random.nextInt(100);
                while(incorrectAnswer == num1+num2)
                {
                    incorrectAnswer = random.nextInt(100);

                }

            }
            answer.add(incorrectAnswer);
        }


        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));


    }

    public void choseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfAnswer)))
        {
            correctText.setText("Correct !");
            score++;
        }

        else
        {
            correctText.setText("Incorrect !");
        }

        totalQuestions++;
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));
        generateQuestion(findViewById(R.id.button0));
    }

    public void playAgain(View view)
    {
        scoreText.setText("0/0");
        timerText.setText("60s");
        correctText.setText("");

        playAgain.setVisibility(View.INVISIBLE);

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);


        new CountDownTimer(60100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerText.setText(String.valueOf(millisUntilFinished/1000) + "s");
              //  if (Integer.parseInt(timerText.toString()) <= 10)
                {
                   timerText.setTextColor(Color.parseColor("#FF0000"));
                    sumText.setTextColor(Color.parseColor("#1565C0"));
                    scoreText.setTextColor(Color.parseColor("#6A1B9A"));
                }
            }


            @Override
            public void onFinish() {

                timerText.setText("0s");
                correctText.setText("your score: " + Integer.toString(score) + "/" + Integer.toString(totalQuestions));
                playAgain.setVisibility(View.VISIBLE);
                showScore.setVisibility(View.VISIBLE);
                scoreString = score;
                shareButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                sumText.setText("");
             //  timerText.setTextColor(Color.parseColor("#FFFFFF"));

            }
        }.start();

        generateQuestion(findViewById(R.id.button3));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoButton = (Button)findViewById(R.id.goButton);
        sumText = (TextView)findViewById(R.id.sumText);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        correctText = (TextView)findViewById(R.id.correctText);
        scoreText = (TextView)findViewById(R.id.scoreText);
        timerText = (TextView)findViewById(R.id.timerText);
        playAgain = (Button)findViewById(R.id.playAgainButton);
        shareButton = (Button)findViewById(R.id.shareButton);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        showScore = (Button)findViewById(R.id.scoreButton);



    }
    public void scoreButtonm(View view)
    {
        Intent intent = new Intent(getBaseContext(), score.class);
        intent.putExtra("score", Integer.toString(score));
        startActivity(intent);

    }

    public void shareButtonm(View view)
    {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "BRAINLY");
            String sAux = "\nHey! I scored " + scoreString + " out of "+ totalQuestions+" on BRAINLY. Get it here :-   https://github.com/rishabh-modi/Brainly ";
            //  sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }

    }

}

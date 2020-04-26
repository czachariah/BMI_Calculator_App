package com.example.bmi_calculator;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Chris Zachariah (cvz2)
 * @author Anthony Topol (at877)
 */

public class SecondActivity extends AppCompatActivity
{
    char type = 'U';

    TextView viewAdvice;

    ImageView picOfType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // make a back button on top
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get info from the last screen
        type = getIntent().getExtras().getChar("advice");

        viewAdvice = findViewById(R.id.AdviceView);
        picOfType = findViewById(R.id.AdvicePicture);
        if (type == 'U')
        {
            viewAdvice.setText("You are: Underweight");
            picOfType.setImageResource(R.drawable.under_weight);
        }
        else if (type == 'N')
        {
            viewAdvice.setText("You are: Normal Weight");
            picOfType.setImageResource(R.drawable.normal_weight);
        }
        else if (type == 'O')
        {
            viewAdvice.setText("You are: Overweight");
            picOfType.setImageResource(R.drawable.over_weight);
        }
        else
        {
            viewAdvice.setText("You are: Obese");
            picOfType.setImageResource(R.drawable.obese_weight);
        }
    } // onCreate()
} // SecondActivity

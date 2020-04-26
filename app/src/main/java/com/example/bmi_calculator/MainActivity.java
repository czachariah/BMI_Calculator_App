package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

/**
 * @author Chris Zachariah (cvz2)
 * @author Anthony Topol (at877)
 */
public class MainActivity extends AppCompatActivity
{
    final char UNDER_WEIGHT = 'U';
    final char NORMAL_WEIGHT = 'N';
    final char OVER_WEIGHT = 'O';
    final char OBESE = 'B';

    char advice = UNDER_WEIGHT;

    // Radio Group + Radio Button Choice
    RadioGroup chooseMetric;
    RadioButton choiceMetric;

    // Height and Weight Input
    TextView heightInput;
    TextView weightInput;

    // BMI display
    TextView BMIDisplay;

    // Buttons
    Button calcBMIButton;
    Button getAdviceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BMIDisplay = findViewById(R.id.BMICalculation);
        heightInput = findViewById(R.id.HeightInput);
        weightInput = findViewById(R.id.WeightInput);
        chooseMetric = findViewById(R.id.radioGroup);
        calcBMIButton = findViewById(R.id.CalcBMIButton);
        getAdviceButton = findViewById(R.id.GetAdviceButton);

        chooseMetric.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                choiceMetric = chooseMetric.findViewById(chooseMetric.getCheckedRadioButtonId());
                if (choiceMetric.getText().equals("Pounds and Inches"))
                {
                    weightInput.setHint("Weight in Pounds");
                    heightInput.setHint("Height in Inches");

                }
                else
                {
                    weightInput.setHint("Weight in Kilograms");
                    heightInput.setHint("Height in Meters");
                }
            } // onCheckedChanged
        });

        calcBMIButton.setOnClickListener(new View.OnClickListener()
        {
            Double weight = 0.0;
            Double height = 0.0;
            @Override
            public void onClick(View view)
            {
                if (weightInput.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"PLEASE ENTER YOUR WEIGHT!" , Toast.LENGTH_SHORT).show();
                    return;
                }
                try
                {
                    weight = Double.parseDouble(weightInput.getText().toString());
                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this,"PLEASE ENTER A DECIMAL NUMBER FOR WEIGHT!" , Toast.LENGTH_SHORT).show();
                    return;
                }

                if (heightInput.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"PLEASE ENTER YOUR HEIGHT!" , Toast.LENGTH_SHORT).show();
                    return;
                }
                try
                {
                    height = Double.parseDouble(heightInput.getText().toString());
                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this,"PLEASE ENTER A DECIMAL NUMBER FOR HEIGHT!" , Toast.LENGTH_SHORT).show();
                    return;
                }

                choiceMetric = chooseMetric.findViewById(chooseMetric.getCheckedRadioButtonId());
                if (choiceMetric.getId() == R.id.radioButton1)
                {
                    Double numerator = weight * 703;
                    Double denominator = height * height;
                    Double BMI = numerator / denominator;
                    BMIDisplay.setText(String.format("%.2f",BMI));

                    if (BMI < 18.5)
                    {
                        advice = UNDER_WEIGHT;
                    }
                    else if ((BMI >= 18.5) && (BMI <= 24.9))
                    {
                        advice = NORMAL_WEIGHT;
                    }
                    else if ((BMI >= 25) && (BMI <= 29.9))
                    {
                        advice = OVER_WEIGHT;
                    }
                    else
                    {
                        advice = OBESE;
                    }
                }
                else
                {
                    Double numerator = weight;
                    Double denominator = height * height;
                    Double BMI = numerator / denominator;
                    BMIDisplay.setText(String.format("%.2f",BMI));

                    if (BMI < 18.5)
                    {
                        advice = UNDER_WEIGHT;
                    }
                    else if ((BMI >= 18.5) && (BMI <= 24.9))
                    {
                        advice = NORMAL_WEIGHT;
                    }
                    else if ((BMI >= 25) && (BMI <= 29.9))
                    {
                        advice = OVER_WEIGHT;
                    }
                    else
                    {
                        advice = OBESE;
                    }
                }
            } // onClick()
        });

        getAdviceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (BMIDisplay.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"PLEASE CALCULATE BMI FIRST!" , Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("advice", advice);
                startActivity(intent);
            } // onClick()
        });
    } // onCreate()
} // MainActivity

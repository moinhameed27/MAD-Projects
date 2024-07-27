package com.example.guesstheword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Declaring Array of String
    String[] places = new String[]{
            "Paris", "London", "Newyork", "Tokyo", "Rome", "Sydney", "Istanbul", "Dubai", "Moscow", "Beijing", "Quetta",
            "Cairo", "Rio", "Berlin", "Amsterdam", "Losangeles", "Mumbai", "Barcelona", "Seoul", "Bangkok",
            "Vienna", "Toronto", "Madrid", "Singapore", "Venice", "Lisbon", "SanFrancisco", "Sargodha", "Sukkur", "Larkana",
            "Melbourne", "Montreal", "Shanghai", "Capetown", "Stockholm", "Dublin", "Helsinki", "Kualalumpur", "Faisalabad",
            "Oslo", "Copenhagen", "Budapest", "Santiago", "Lima", "Riyadh", "Jakarta", "AbuDhabi", "Jhang", "Gujrat", "Kasur",
            "Delhi", "Nairobi", "Manila", "Auckland", "Doha", "Bogota", "Riyadh", "Oslo", "Nairobi", "Agra", "Pune", "Surat",
            "Sandiego", "Houston", "Miami", "Dallas", "Phoenix", "Denver", "Boston", "Atlanta", "Indore", "Delhi", "Lucknow",
            "Washingtondc", "Lasvegas", "Honolulu", "Vancouver", "Kolkata", "Okara", "Islamabad", "Multan", "Bangalore",
            "Mumbai", "Karachi", "Istanbul", "Tehran", "Baghdad", "Riyadh", "Kabul", "Dhaka", "Lahore", "Bangkok", "Jakarta",
            "Sydney", "Auckland", "Moscow", "Cairo", "Dublin", "Berlin", "Hanoi", "Kuala lumpur", "Budapest", "Vienna",
            "Stockholm", "Seoul", "Manila", "Bangkok", "Kolkata", "Mexico city", "Toronto", "Montreal", "Mailsi", "Austin",
            "Vancouver", "Sydney", "Melbourne", "Wellington", "Auckland", "Dubai", "Istanbul", "Chicago", "Dallas",
            "Athens", "Madrid", "Barcelona", "Milan", "Rome", "Oslo", "Helsinki", "Copenhagen", "Amsterdam", "Galle", "Jaffna",
            "Warsaw", "Lisbon", "Moscow", "Sofia", "Khulna", "Sylhet", "Rangpur", "Comilla", "Barisal", "Colombo", "Kandy",
            "Jakarta", "Bangkok", "Hanoi", "Manila", "KualaLumpur", "Singapore", "Seoul", "Tokyo", "Osaka", "Kyoto", "Hiroshima",
            "Shanghai", "Shenzhen", "Tianjin", "Hongkong", "Macau", "Chengdu", "Wuhan", "Beijing", "Bristol", "Leeds", "Cardiff"
    };


    String day;
    Random random;

    TextView txtCorrectAnswer, txtRightAnswer, txtQuestion;
    EditText etUserInput;
    Button btCheck, btShow, btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer);
        txtRightAnswer = findViewById(R.id.txtRightAnswer);
        txtQuestion = findViewById(R.id.txtQuestion);

        etUserInput = findViewById(R.id.etUserInput);

        btCheck = findViewById(R.id.btCheck);
        btShow = findViewById(R.id.btShow);
        btNext = findViewById(R.id.btNext);

        random = new Random();

        day = places[random.nextInt(places.length)];
        txtQuestion.setText(mixWords(day));

        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUserInput.getText().toString().equalsIgnoreCase(day))
                {
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.correct_dialog);
                    Button btHide = dialog.findViewById(R.id.btContinue);
                    dialog.show();

                    btHide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etUserInput.setText("");
                            day = places[random.nextInt(places.length)];
                            txtQuestion.setText(mixWords(day));
                            txtRightAnswer.setVisibility(View.INVISIBLE);
                            txtCorrectAnswer.setVisibility(View.INVISIBLE);
                            dialog.dismiss();
                        }
                    });

                }
                else
                {
                    Toast.makeText(MainActivity.this, "You are Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = places[random.nextInt(places.length)];
                txtQuestion.setText(mixWords(day));

                etUserInput.setText("");
                txtRightAnswer.setVisibility(View.INVISIBLE);
                txtCorrectAnswer.setVisibility(View.INVISIBLE);
            }
        });

        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCorrectAnswer.setVisibility(View.VISIBLE);
                txtRightAnswer.setVisibility(View.VISIBLE);

                txtRightAnswer.setText(day);
            }
        });

    }


    private String mixWords(String word)
    {
        List<String> words = Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed = "";

        for(String i : words)
        {
            mixed += i;
        }
        return mixed;
    }
}
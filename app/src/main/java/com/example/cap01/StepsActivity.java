package com.example.cap01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StepsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Button buttonHome=(Button)findViewById(R.id.buttonHome);
        Button buttonSteps=(Button)findViewById(R.id.buttonSteps);
        Button buttonStats=(Button)findViewById(R.id.buttonStats);
        Button buttonDiet=(Button)findViewById(R.id.buttonDiet);

        buttonDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DietActivity.class);
                startActivity(intent);
            }
        });
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StatsActivity.class);
                startActivity(intent);
            }
        });
    }
}

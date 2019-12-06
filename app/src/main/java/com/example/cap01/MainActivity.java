package com.example.cap01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView_Weight = (TextView)findViewById(R.id.textView_Weight) ;
        TextView textView_BMI = (TextView)findViewById(R.id.textView_BMI) ;
        TextView textView_Calories = (TextView)findViewById(R.id.textView_Calories) ;
        Button buttonSetting=(Button)findViewById(R.id.buttonSetting);
        Button buttonSteps=(Button)findViewById(R.id.buttonSteps);
        Button buttonStats=(Button)findViewById(R.id.buttonStats);
        Button buttonDiet=(Button)findViewById(R.id.buttonDiet);

        Intent intent=getIntent();

        /*
        String Weight=intent.getExtras().getString("Weight");
        String Height=intent.getExtras().getString("height");


        textView_Weight.setText(Weight);

        double bmi= Double.parseDouble(Weight)/(Double.parseDouble(Height)*Double.parseDouble(Height))*10000;
        textView_BMI.setText(Double.toString(bmi));

        double bm;
        bm=66.47+13.75*Double.parseDouble(Weight)-6.70*25;
        textView_Calories.setText(Double.toString(bm));
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),InfoActivity.class);

                intent1.putExtra("comment","수정합니다.");

                startActivity(intent1);
            }
        });
        */
        buttonDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),DietActivity.class);
                startActivity(intent2);
            }
        });
        buttonSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(),StepsActivity.class);
                startActivity(intent3);
            }
        });
        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(),StatsActivity.class);
                startActivity(intent4);
            }
        });



    }

}

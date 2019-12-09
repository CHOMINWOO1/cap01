package com.example.cap01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;


public class StepsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    TextView tvStepDetector;
    private int mStepDetector;

    private LineView lineView;
    private LineView lineView1;
    //수정부분
    private FirebaseFirestore db= FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        getAllDocs();
        tvStepDetector = (TextView)findViewById(R.id.tvStepDetector);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if(stepDetectorSensor == null) {
            Toast.makeText(this, "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }

        Button buttonHome=(Button)findViewById(R.id.buttonHome);
        Button buttonSteps=(Button)findViewById(R.id.buttonSteps);
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

    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if(event.values[0] == 1.0f) {
                mStepDetector++;
                tvStepDetector.setText(String.valueOf(mStepDetector)+"/10000");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void getAllDocs() {
        final ArrayList<Integer> Weight_ArrayList= new ArrayList<>();
        final ArrayList<String> Date_ArrayList= new ArrayList<>();
        db.collection("개인 정보")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override

                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String WeightAdapter = document.get("체중").toString();
                                Weight_ArrayList.add(Integer.parseInt(WeightAdapter));
                                // Weight_ArrayList.add(30);
                                Date_ArrayList.add(document.getId().toString());
                            }
                        }
                        lineView = (LineView) findViewById(R.id.line_view);


                        // put data sets into datalist
                        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();

                        dataLists.add(Weight_ArrayList);
                        //dataLists.add(dataList_3);

                        // draw line graph
                        lineView.setDrawDotLine(true);                             //마커
                        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);       //SHOW_POPUPS_All = 1(모든 데이터 값 보기), SHOW_POPUPS_MAXMIN_ONLY = 2 최솟값, 최대값만 보여줌, NONE 아무것도 안보임
                        lineView.setColorArray(new int[]{
                                Color.parseColor("#e74c3c")//  Color.parseColor("#1abc9c")
                        });
                        lineView.setBottomTextList(Date_ArrayList);   // hour란 이름의 string array list 위에서 만듬
                        lineView.setDataList(dataLists);    // dataList란 이름의 integer array list 위에서 만듬


                    }
                });
    }
    /*
    public void getAllDocs2() {
         final ArrayList<Integer> Calories_ArrayList= new ArrayList<>();
         final ArrayList<String> Date1_ArrayList= new ArrayList<>();
        db.collection("칼로리")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override

                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String CaloriesAdapter = document.get("칼로리").toString();
                                Calories_ArrayList.add(Integer.parseInt(WeightAdapter));
                                Date1_ArrayList.add(document.getId().toString());
                            }
                        }
                        lineView1 = (LineView1) findViewById(R.id.line_view);


                        // put data sets into datalist
                        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();

                        dataLists.add(Calories_ArrayList);

                        // draw line graph
                        lineView1.setDrawDotLine(true);                             //마커
                        lineView1.setShowPopup(LineView1.SHOW_POPUPS_NONE);       //SHOW_POPUPS_All = 1(모든 데이터 값 보기), SHOW_POPUPS_MAXMIN_ONLY = 2 최솟값, 최대값만 보여줌, NONE 아무것도 안보임
                        lineView1.setColorArray(new int[]{
                                Color.parseColor("#e74c3c")//  Color.parseColor("#1abc9c")
                        });
                        lineView1.setBottomTextList(Date1_ArrayList);   // hour란 이름의 string array list 위에서 만듬
                        lineView1.setDataList(dataLists);    // dataList란 이름의 integer array list 위에서 만듬
                    }
                });
    }

     */
}

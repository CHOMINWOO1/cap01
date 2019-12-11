package com.example.cap01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "출력";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String weight;
    String height;
    TextView textView_Weight;
    TextView textView_BMI;
    TextView textView_Calories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_Weight = (TextView)findViewById(R.id.textView_Weight) ;
        textView_BMI = (TextView)findViewById(R.id.textView_BMI) ;
        textView_Calories = (TextView)findViewById(R.id.textView_Calories) ;
        Button buttonSetting=(Button)findViewById(R.id.buttonSetting);
        Button buttonSteps=(Button)findViewById(R.id.buttonSteps);
       // Button buttonStats=(Button)findViewById(R.id.buttonStats);
        Button buttonDiet=(Button)findViewById(R.id.buttonDiet);


        Intent intent=getIntent();
/*
        DocumentReference docRef = db.collection("개인 정보").document("asd");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        */
        getAllDocs();
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),InfoActivity.class);
                intent1.putExtra("check",true);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
        buttonDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),DietActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
        buttonSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(),StepsActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
            }
        });




    }

    public void getAllDocs() {
        // [START get_multiple_all]
        db.collection("개인 정보")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId().toString());
                                weight= document.get("체중").toString();
                                height=document.get("키").toString();
                            }
                            textView_Weight.setText(weight);
                            double bmi= Double.parseDouble(weight)/(Double.parseDouble(height)*Double.parseDouble(height))*10000;
                            textView_BMI.setText(Double.toString(bmi));
                            double cal=Double.parseDouble(weight)*31.12;
                            textView_Calories.setText(Double.toString(cal));
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        // [END get_multiple_all]
    }
}


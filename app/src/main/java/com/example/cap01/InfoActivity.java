package com.example.cap01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class InfoActivity extends AppCompatActivity {
    Button saveBtn;
    EditText etWeight,etGoalWeight,etHeight;
    datePicker dp=new datePicker();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "입력";
    //Intent intent=getIntent();

  //  String a=intent.getExtras().getString("Comment");
   // TextView textView1 = (TextView)findViewById(R.id.TextView1);


    public boolean isNumeric(String input) { //숫자인지 확인하기 위한 함수
        try {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //textView1.setText(a);
        saveBtn=(Button)findViewById(R.id.btnSave);
        etWeight=(EditText)findViewById(R.id.etWeight);
        etHeight=(EditText)findViewById(R.id.etHeight);
        etGoalWeight=(EditText)findViewById(R.id.etGoalWeight);

        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();
        String goalw = etGoalWeight.getText().toString();
        /*
        Integer weight = Integer.parseInt(etWeight.getText().toString());
        Integer height = Integer.parseInt(etHeight.getText().toString());
        Integer goalw = Integer.parseInt(etGoalWeight.getText().toString());*/
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNumeric(etWeight.getText().toString())&&isNumeric(etWeight.getText().toString())&&isNumeric(etWeight.getText().toString()))
                {

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    Map<String, Integer> textField = new HashMap<>();
                    Integer weight = Integer.parseInt(etWeight.getText().toString());
                    Integer height = Integer.parseInt(etHeight.getText().toString());
                    Integer goalw = Integer.parseInt(etGoalWeight.getText().toString());
                    textField.put("체중",weight); //맵에 값 입력
                    textField.put("키",height);
                    textField.put("목표체중",goalw);

                    db.collection("개인 정보")
                            .add(textField)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG,"Document has been saved!");
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Document was not saved!",e);
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

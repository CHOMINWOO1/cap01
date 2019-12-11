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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    String weight;
    String height;
    String goalw;
    boolean check;
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
        Intent intent = getIntent();
        check=intent.getBooleanExtra("check",false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        if(!check)
            getAllDocs();
        saveBtn=(Button)findViewById(R.id.btnSave);
        etWeight=(EditText)findViewById(R.id.etWeight);
        etHeight=(EditText)findViewById(R.id.etHeight);
        etGoalWeight=(EditText)findViewById(R.id.etGoalWeight);

        weight = etWeight.getText().toString();
        height = etHeight.getText().toString();
        goalw = etGoalWeight.getText().toString();
        /*
        Integer weight = Integer.parseInt(etWeight.getText().toString());
        Integer height = Integer.parseInt(etHeight.getText().toString());
        Integer goalw = Integer.parseInt(etGoalWeight.getText().toString());*/
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight.equals("")||height.equals("")||goalw.equals(""))
                {
                    Map<String, Integer> textField = new HashMap<>();
                    int weight = Integer.parseInt(etWeight.getText().toString());
                    int height = Integer.parseInt(etHeight.getText().toString());
                    int goalw = Integer.parseInt(etGoalWeight.getText().toString());
                    textField.put("체중",weight); //맵에 값 입력
                    textField.put("키",height);
                    textField.put("목표체중",goalw);
                    db.collection("개인 정보")
                            .document(dp.datepick())
                            .set(textField).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG,"Document has been saved!");
                          //  Log.d(TAG,db.collection("개인 정보").document().getId()+"asdasd");
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Document was not saved!", e);
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
    public void getAllDocs() {
        // [START get_multiple_all]
        db.collection("개인 정보")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot document = task.getResult();
                            if (document.isEmpty()) {

                            }
                            else {
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                           }

                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });
        // [END get_multiple_all]
    }
}

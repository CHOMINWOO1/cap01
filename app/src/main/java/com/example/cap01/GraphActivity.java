package com.example.cap01;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;

public class GraphActivity extends AppCompatActivity {       //프래그먼트라서 findViewById가 안되니 rootView.findViewById를 써야 한다
    //this 대신 getActivity()를 이용하여 context를 얻는 다는 점! db = new SQLiteDatabaseHandler(getActivity());
    //이 외에는 그냥 액티비티 코드랑 같다
    private LineView lineView;
    //수정부분
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    ArrayList<Integer> Weight_ArrayList= new ArrayList<Integer>();
    ArrayList<String> Date_ArrayList= new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp);
        getAllDocs(); //온크리에이트 들어가자마자 실행
        lineView = (LineView) findViewById(R.id.line_view);


        // list data
        //List<AirQualityData> data = db.todayAirQualityData();

        // lable
        ArrayList<String> hour = new ArrayList<String>();
        //        // 3 data sets
        ArrayList<String> Date_ArrayList= new ArrayList<>();
        // ArrayList<Integer> dataList_2 = new ArrayList<>();        //선추가2
        //ArrayList<Integer> dataList_3 = new ArrayList<>();         //선추가2

        // put db data into arrays
        //for (AirQualityData datum : data) {
        //         hour.add(String.valueOf(datum.getHour()));
        //    dataList_1.add(datum.getPm1());
        //dataList_2.add(datum.getPm2());
        //dataList_3.add(datum.getPm3());
        // }

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

    //맨아래에 함수선언
    public void getAllDocs() {
        // [START get_multiple_all]
        db.collection("개인 정보")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Weight_ArrayList.add((Integer) document.get("체중"));
                                Date_ArrayList.add(document.getId().toString());
                            }
                        } else {
                        }
                    }
                });
        // [END get_multiple_all]
    }

}
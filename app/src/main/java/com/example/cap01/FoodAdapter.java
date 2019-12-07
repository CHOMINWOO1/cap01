package com.example.cap01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private ArrayList<Food> arrayList;
    private Context context;

    public FoodAdapter(ArrayList<Food> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        FoodViewHolder holder = new FoodViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        //이미지를 불러올때 보조해주는 Glide
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getFoodImage())
                .into(holder.imageView);
        holder.tv_name.setText(arrayList.get(position).getFoodName());
        holder.tv_cal.setText(String.valueOf(arrayList.get(position).getFoodCal()));
    }

    @Override
    public int getItemCount() {
        //사망 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name, tv_cal;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_cal = itemView.findViewById(R.id.tv_cal);
        }
    }
}

package com.example.hikeapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ViewHolder> {
    ArrayList<Hike> mylist;
    Context context;

    public HikeAdapter(ArrayList<Hike> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public HikeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_hikes, parent, false );

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HikeAdapter.ViewHolder holder, int position) {
        holder.tv_location.setText(mylist.get(position).getLocation());
        holder.tv_hikeName.setText(mylist.get(position).getHikeName());
        holder.tv_difficulty.setText(mylist.get(position).getDifficulty());

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_hikeName,tv_location,tv_difficulty;
        public Button btn_detailHike,btn_editHike,btn_deleteHike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        tv_hikeName = (TextView) itemView.findViewById(R.id.tv_hikeName);
        tv_location = (TextView) itemView.findViewById(R.id.tv_location);
        tv_difficulty = (TextView) itemView.findViewById(R.id.tv_difficulty);
        btn_editHike = itemView.findViewById(R.id.btn_editHike);
        btn_deleteHike = itemView.findViewById(R.id.btn_deleteHike);
        btn_detailHike = itemView.findViewById(R.id.btn_detailHike);
        }

    }
}
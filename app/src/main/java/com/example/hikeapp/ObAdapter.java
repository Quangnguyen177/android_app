package com.example.hikeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObAdapter extends RecyclerView.Adapter<ObAdapter.ViewHolder> {
    ArrayList<Observation> myOb;
    Context context;
    String hikeId;

    public ObAdapter(ArrayList<Observation> myOb, Context context, String hikeId) {
        this.myOb = myOb;
        this.context = context;
        this.hikeId = hikeId;
    }

    @NonNull
    @Override
    public ObAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_observations, parent, false );
        ObAdapter.ViewHolder viewHolder = new ObAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ObAdapter.ViewHolder holder, int position) {

        holder.tv_obName.setText(myOb.get(position).getObservationName());
        holder.tv_obTime.setText(myOb.get(position).getObservationTime());
        holder.tv_obComment.setText(myOb.get(position).getAdditionComment());
        holder.imgOb.setImageURI(Uri.parse(myOb.get(position).getImageOb()));
        holder.btn_detailOb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailObservationActivity.class );
                intent.putExtra("idOb", myOb.get(position).getIdOb());
                intent.putExtra("observationName", myOb.get(position).getObservationName());
                intent.putExtra("observationTime", myOb.get(position).getObservationTime());
                intent.putExtra("additionComment", myOb.get(position).getAdditionComment());
                intent.putExtra("imageOb", myOb.get(position).getImageOb());
                context.startActivity(intent);
            }
        });
        holder.btn_deleteOb.setOnClickListener(new View.OnClickListener() {
            DBHelper dbHelper = new DBHelper(context);
            @Override
            public void onClick(View view) {
                dbHelper.deleteOb(myOb.get(position).getIdOb());
                Intent intent = new Intent(context, AllObservationActivity.class);
                intent.putExtra("idHike", hikeId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOb.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_obName, tv_obTime, tv_obComment;
        public Button btn_detailOb, btn_deleteOb;
        public ImageView imgOb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_obName = itemView.findViewById(R.id.tv_obName);
            tv_obTime = itemView.findViewById(R.id.tv_obTime);
            tv_obComment = itemView.findViewById(R.id.tv_obComment);
            imgOb = itemView.findViewById(R.id.imgOb);
            btn_detailOb = itemView.findViewById(R.id.btn_detailOb);
            btn_deleteOb = itemView.findViewById(R.id.btn_deleteOb);
        }
    }
}

package com.example.hikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailObservationActivity extends AppCompatActivity {

    ImageView btnBack, imageViewOb;
    Button btn_editOb;
    TextView dt_observationName,dt_observationTime,dt_additionComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_observation);
        link();
        eventBack();
         btn_editOb = findViewById(R.id.btn_editOb);
         dt_observationName = findViewById(R.id.dt_observationName);
         dt_observationTime = findViewById(R.id.dt_observationTime);
         dt_additionComment = findViewById(R.id.dt_additionComment);
         imageViewOb = findViewById(R.id.ed_imageView);


        String idOb = getIntent().getStringExtra("idOb");
        String observationName = getIntent().getStringExtra("observationName");
        String observationTime = getIntent().getStringExtra("observationTime");
        String additionComment = getIntent().getStringExtra("additionComment");
        String imageView = getIntent().getStringExtra("imageOb");

        dt_observationName.setText(observationName);
        dt_observationTime.setText(observationTime);
        dt_additionComment.setText(additionComment);
        imageViewOb.setImageURI(Uri.parse(imageView));
        Log.e( "imageViwe dcm: ",imageView);
        btn_editOb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(DetailObservationActivity.this, EditObservationActivity.class);
                    intent.putExtra("idOb", idOb);
                    intent.putExtra("observationName", observationName);
                    intent.putExtra("observationTime", observationTime);
                    intent.putExtra("additionComment", additionComment);
                    intent.putExtra("imageOb", imageView);
                    DetailObservationActivity.this.startActivity(intent);
                } catch (Exception e) {
                    Log.e("onClickEdit: ", e.toString());
                }
            }
        });
    }
    private void eventBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void link() {
        btnBack = findViewById(R.id.btn_back);
    }
}
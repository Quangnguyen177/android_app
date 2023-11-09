package com.example.hikeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditObservationActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 200;
    private static final int IMG_FROM_GALLERY_CODE = 300;
    private static final int IMG_FROM_CAMERA_CODE = 400;
    private String[] cameraPermission;
    private String[] storagePermission;
    EditText ed_observationName,ed_additionComment,ed_observationTime;
    ImageView btnBack, imageViewOb;
    Uri imageUri;
    Button btn_saveOb, btn_edImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_observation);
        cameraPermission = new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        link();
        eventBack();
        ed_observationName= findViewById(R.id.dt_observationName);
        ed_observationTime = findViewById(R.id.dt_observationTime);
        ed_additionComment = findViewById(R.id.dt_additionComment);
        imageViewOb = findViewById(R.id.ed_imageView);
        btn_saveOb = findViewById(R.id.btn_editOb);
        btn_edImage = findViewById(R.id.btn_edImage);

        String imageOb = getIntent().getStringExtra("imageOb");
        String observationName = getIntent().getStringExtra("observationName");
        String observationTime = getIntent().getStringExtra("observationTime");
        String additionComment = getIntent().getStringExtra("additionComment");

        ed_observationName.setText(observationName);
        ed_observationTime.setText(observationTime);
        ed_additionComment.setText(additionComment);


        if( imageOb != null){
            imageUri = Uri.parse(imageOb);
            imageViewOb.setImageURI(imageUri);
        }
        imageUri = Uri.parse(imageOb);
        btn_saveOb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(EditObservationActivity.this);
                String idOb= getIntent().getStringExtra("idOb");
                String obName = ed_observationName.getText().toString();
                String obTime = ed_observationTime.getText().toString();
                String addCmt = ed_additionComment.getText().toString();
                dbHelper.editObs(idOb,obName,obTime,addCmt, String.valueOf(imageUri));
                Toast.makeText(EditObservationActivity.this ,"Update successfully", Toast.LENGTH_SHORT).show();
            }
        });
        btn_edImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickerDialog();
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
    private void showImagePickerDialog() {
        String[] options = {"Camera","Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose An Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    if(!checkCamera()){
                        requestCamera();
                    }else{
                        pickFromCamera();
                    }
                }else if(which == 1){
                    if(!checkStorage()){
                        requestStorage();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        }).create().show();
    }


    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMG_FROM_GALLERY_CODE);
    }

    private void requestStorage() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_PERMISSION_CODE);
    }

    private boolean checkStorage() {
        boolean result1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result1;
    }



    private void pickFromCamera() {
        if (!isCameraAvailable()) {
            Toast.makeText(this, "No camera app found on your device.", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "IMAGE_TITLE");
        values.put(MediaStore.Images.Media.DESCRIPTION, "IMAGE_DETAIL");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(cameraIntent, IMG_FROM_CAMERA_CODE);
    }


    private void requestCamera() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_PERMISSION_CODE);
    }
    private boolean isCameraAvailable() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        return cameraIntent.resolveActivity(getPackageManager()) != null;
    }

    private boolean checkCamera() {
        boolean result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result & result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_PERMISSION_CODE:
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    } else {
                        Toast.makeText(getApplicationContext(), "Camera & Storage Permission Needs ...", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case STORAGE_PERMISSION_CODE:
                if(grantResults.length > 0){
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromGallery();
                    } else {
                        Toast.makeText(getApplicationContext(), "Storage Permission Needs ...", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMG_FROM_GALLERY_CODE) {
                // Cập nhật imageUri bằng Uri của ảnh đã chọn từ thư viện
                imageUri = data.getData();
                imageViewOb.setImageURI(imageUri);
            }
        }
    }
}
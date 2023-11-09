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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ObservationActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 200;
    private static final int IMG_FROM_GALLERY_CODE = 300;
    private static final int IMG_FROM_CAMERA_CODE = 400;
    private String[] cameraPermission;
    private String[] storagePermission;

    private Uri imageUri;
    EditText observationName, observationTime, additionComment;
    Button btn_addOb, btn_Image;

    ImageView btnBack, imageView;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraPermission = new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        setContentView(R.layout.activity_observation);
        observationName = findViewById(R.id.dt_observationName);
        observationTime = findViewById(R.id.dt_observationTime);
        additionComment = findViewById(R.id.dt_additionComment);
        btn_addOb = findViewById(R.id.btn_editOb);
        btn_Image = findViewById(R.id.btn_edImage);
        imageView = findViewById(R.id.ed_imageView);
        String obIdHike = getIntent().getStringExtra("idHike");
        dbHelper = new DBHelper(this);
//        link();
//        eventBack();
        btn_addOb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String obName = observationName.getText().toString();
                if (TextUtils.isEmpty(obName)) {
                    observationName.setError("Name of observation is required");
                    return; // Dừng lại và không thực hiện thêm dữ liệu
                }
                String obTime = observationTime.getText().toString();
                if (TextUtils.isEmpty(obTime)){
                    observationTime.setError("Time of observation is required");
                }
                String addComment = additionComment.getText().toString();
                dbHelper.insert_Ob(obName,obTime,addComment,obIdHike, String.valueOf(imageUri));

            }
        });
        btn_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickerDialog();
            }
        });
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
                imageView.setImageURI(imageUri);
            }
        }
    }
//    private void eventBack() {
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//    }
//
//    private void link() {
//        btnBack = findViewById(R.id.btn_back);
//    }
}
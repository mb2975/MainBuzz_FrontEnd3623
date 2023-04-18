package com.example.themainbuzzlogin2;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.TextView;
//
//public class UserAreaActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_area);
//
//        final EditText etName = (EditText) findViewById(R.id.etName);
//        final EditText etAge = (EditText) findViewById(R.id.etAge); // ignore these for now - JG
//        final TextView welcomeMessage = (TextView) findViewById(R.id.textView2);
//    }
//}


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UserAreaActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 22;
    Button btnpicture;
    Button saveImg;
    ImageView imageView;
    OutputStream outputStream;

    ImageView FrBtLink;

    Button BTN;

    //Uploading Image as Post - JG

    File compressedImageFile = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get rid of Action bar and title, to my knowledge, this is the only way to do so and you must do it in every activity - JG
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        btnpicture = findViewById(R.id.btncamera_id);
        saveImg = findViewById(R.id.saveimgbtn);
        imageView  = findViewById(R.id.imageview1);

        FrBtLink = findViewById(R.id.FrBtLink);

        BTN = findViewById(R.id.button);

        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotoFeedActivity.class);
                startActivity(intent);
            }
        });

        btnpicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CODE);
            }
        });

        FrBtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendsActivity.class);
                startActivity(intent);
            }
        });

        saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(UserAreaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    saveImage();
                }
                else{
                    askPermission();
                }
            }
        });
    }

    private void askPermission(){
        ActivityCompat.requestPermissions(UserAreaActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveImage();
            }
            else{
                Toast.makeText(UserAreaActivity.this, "Please provide the required permissions", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void saveImage(){
        File dir = new File(Environment.getExternalStorageDirectory(), "SaveImage");



        if(!dir.exists()){
            dir.mkdir();
        }
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        File file = new File(dir, System.currentTimeMillis()+".jpg");
        try {
            outputStream = new FileOutputStream(file);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        Toast.makeText(UserAreaActivity.this, "Successfully Saved", Toast.LENGTH_SHORT).show();

        try{
            outputStream.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            outputStream.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data"); //compressed version of image; will need to find another tutorial for full image
            imageView.setImageBitmap(photo);
        }
        else {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
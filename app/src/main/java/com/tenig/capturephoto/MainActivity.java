package com.tenig.capturephoto;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button DisplayList;
    Button TakePhoto;
    ImageView DisplayImage;
    Intent intent;
    Bitmap bitmap;
    public static final int RequestPermissionCode = 1;
    public static final int REQUEST_IMAGE_CAPTURE = 7;
    DataBaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayList = findViewById(R.id.DisplayList);
        DisplayImage = findViewById(R.id.DisplayImage);
        TakePhoto = findViewById(R.id.TakePhoto);
        /**
         * create DatabaseHandler object
         */
        db = new DataBaseHandler(this);

        EnableRuntimePermissionToAccessCamera();

        TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

            }
        });

        DisplayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            DisplayImage.setImageBitmap(imageBitmap);

            // convert bitmap to byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte imageInByte[] = stream.toByteArray();

            // Inserting Contacts
            Log.d("Insert: ", "Inserting ..");
            db.addLcations(new Locations(10.00f, 10.00f, imageInByte));
        }
    }

    // Requesting runtime permission to access camera.
    public void EnableRuntimePermissionToAccessCamera() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
            // Printing toast message after enabling runtime permission.
            Toast.makeText(MainActivity.this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }
}

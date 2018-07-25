package com.example.jacksonb.connections;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;


public class SignOutActivity extends AppCompatActivity
{
    Button addButton;
    Button sendButton;

    ImageView firstImage;
    ImageView secondImage;
    ImageView thirdImage;
    ImageView forthImage;

    Spinner managerSpinner;

    File imageFile1;
    File imageFile2;
    File imageFile3;
    File imageFile4;

    Uri path1;
    Uri path2;
    Uri path3;
    Uri path4;

    ArrayList<Uri> uriArrayList = new ArrayList<>();

    String emailTo[] = {"dustin.mataczynski@bwpapersystems.com", "dan.jackson@bwpapersystems.com",
            "logan.edinger@bwpapersystems.com", "michael.krans@bwpapersystems.com", "joe.neerdaels@bwpapersystems.com"};

    int imageCount = 0;
    int spinnerSelection;

    boolean imageTaken = false;
    boolean emailSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_out_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        managerSpinner = (Spinner)findViewById(R.id.sign_out_manager_spinner);

        firstImage = (ImageView)findViewById(R.id.first_image_view);
        secondImage = (ImageView)findViewById(R.id.second_image_view);
        thirdImage = (ImageView)findViewById(R.id.third_image_view);
        forthImage = (ImageView)findViewById(R.id.forth_image_view);

        addButton = (Button)findViewById(R.id.sign_out_add_button);
        sendButton = (Button)findViewById(R.id.sign_out_send_button);

        managerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position == 0)
                    spinnerSelection = 0;

                if(position == 1)
                    spinnerSelection = 1;

                if(position == 2)
                    spinnerSelection = 2;

                if(position == 3)
                    spinnerSelection = 3;

                if(position == 4)
                    spinnerSelection = 4;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        addButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        if(imageCount == 0) 
                        {
                            takePicture();
                            imageTaken = true;
                        }

                        if(imageCount == 1)
                        {
                            takeSecondPicture();
                            imageTaken = true;
                        }

                        if(imageCount == 2)
                        {
                            takeThirdPicture();
                            imageTaken = true;
                        }

                        if(imageCount == 3)
                        {
                            takeForthPicture();
                            imageTaken = true;
                        }
                        
                        if(imageCount >= 4)
                            Toast.makeText(SignOutActivity.this, "Can't attach more than 4 images", Toast.LENGTH_SHORT).show();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        sendButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        sendEmail();
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        accessMediaPermission();
    }

    public void takePicture()
    {
        firstImage.setVisibility(View.VISIBLE);

        imageFile1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Sign Out Image.jpg");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile1));
        startActivity(cameraIntent);

        uriArrayList.add(Uri.fromFile(imageFile1));
    }

    public void takeSecondPicture()
    {
        secondImage.setVisibility(View.VISIBLE);

        imageFile2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Sign Out Image 2.jpg");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile2));
        startActivity(cameraIntent);

        uriArrayList.add(Uri.fromFile(imageFile2));
    }

    public void takeThirdPicture()
    {
        thirdImage.setVisibility(View.VISIBLE);

        imageFile3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Sign Out Image 3.jpg");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile3));
        startActivity(cameraIntent);

        uriArrayList.add(Uri.fromFile(imageFile3));
    }

    public void takeForthPicture()
    {
        forthImage.setVisibility(View.VISIBLE);

        imageFile4 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Sign Out Image 4.jpg");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile4));
        startActivity(cameraIntent);

        uriArrayList.add(Uri.fromFile(imageFile4));
    }

    public void sendEmail()
    {
        if(imageCount == 1)
        {
            path1 = Uri.fromFile(imageFile1);
            final String[] to = {emailTo[spinnerSelection]};

            imageCount = 0;

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        emailIntent.setType("application/image");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                        Log.d("test2", emailTo[spinnerSelection]);
                        emailIntent.putExtra(Intent.EXTRA_STREAM, path1);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sign Out");
                        startActivity(emailIntent);

                        emailSent = true;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(SignOutActivity.this, "Error with sending email", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            thread.start();
        }

        if(imageCount == 2)
        {
            path1 = Uri.fromFile(imageFile1);
            path2 = Uri.fromFile(imageFile2);
            final String[] to = {emailTo[spinnerSelection]};

            imageCount = 0;

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        emailIntent.setType("application/image");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriArrayList);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sign Out");
                        startActivity(emailIntent);

                        emailSent = true;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(SignOutActivity.this, "Error with sending email", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            thread.start();
        }

        if(imageCount == 3)
        {
            path1 = Uri.fromFile(imageFile1);
            path2 = Uri.fromFile(imageFile2);
            path3 = Uri.fromFile(imageFile3);
            final String[] to = {emailTo[spinnerSelection]};

            imageCount = 0;

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        emailIntent.setType("application/image");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriArrayList);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sign Out");
                        startActivity(emailIntent);

                        emailSent = true;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(SignOutActivity.this, "Error with sending email", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            thread.start();
        }

        if(imageCount == 4)
        {
            path1 = Uri.fromFile(imageFile1);
            path2 = Uri.fromFile(imageFile2);
            path3 = Uri.fromFile(imageFile3);
            path4 = Uri.fromFile(imageFile4);
            final String[] to = {emailTo[spinnerSelection]};

            imageCount = 0;

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        emailIntent.setType("application/image");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriArrayList);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sign Out");
                        startActivity(emailIntent);

                        emailSent = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SignOutActivity.this, "Error with sending email", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            thread.start();
        }

    }

    public void accessMediaPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if(imageTaken)
            imageCount++;

        if(imageCount == 1 && imageFile1.exists())
        {
            Bitmap firstImageBitmap = BitmapFactory.decodeFile(imageFile1.getAbsolutePath());
            firstImage.setImageBitmap(firstImageBitmap);
        }

        if(imageCount == 2 && imageFile1.exists())
        {
            Bitmap secondImageBitmap = BitmapFactory.decodeFile(imageFile2.getAbsolutePath());
            secondImage.setImageBitmap(secondImageBitmap);
        }

        if(imageCount == 3 && imageFile1.exists())
        {
            Bitmap secondImageBitmap = BitmapFactory.decodeFile(imageFile3.getAbsolutePath());
            thirdImage.setImageBitmap(secondImageBitmap);
        }

        if(imageCount == 4 && imageFile1.exists())
        {
            Bitmap firstImageBitmap = BitmapFactory.decodeFile(imageFile4.getAbsolutePath());
            forthImage.setImageBitmap(firstImageBitmap);
        }

        if(emailSent)
        {
            firstImage.setBackground(getResources().getDrawable(R.drawable.no_image));
            secondImage.setBackground(getResources().getDrawable(R.drawable.no_image));
            thirdImage.setBackground(getResources().getDrawable(R.drawable.no_image));
            forthImage.setBackground(getResources().getDrawable(R.drawable.no_image));

            try
            {
                if(imageFile1.exists())
                    imageFile1.delete();

                if(imageFile2.exists())
                    imageFile2.delete();

                if(imageFile3.exists())
                    imageFile3.delete();

                if(imageFile4.exists())
                    imageFile4.delete();
            }
            catch (Exception e){e.printStackTrace();}

            finish();
        }

        imageTaken = false;
        emailSent = false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}



package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shadspace.soup_it.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class Story_Read extends AppCompatActivity {

    ImageView RS_Image, Back;

    String url;

    TextView RS_Title, RS_Written, RS_Story, RS_Type, RS_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_story__read);


        RS_Written = findViewById(R.id.RS_Written);
        RS_Title = findViewById(R.id.RS_Title);
        RS_Story = findViewById(R.id.RS_Story);
        RS_Type = findViewById(R.id.RS_Type);
        RS_Image = findViewById(R.id.RS_Image);
        RS_URL = findViewById(R.id.RS_URL);
        Back = findViewById(R.id.Back);


        Intent intent = getIntent();
        String Written = intent.getStringExtra("Written");
        String Title = intent.getStringExtra("Title");
        String Story = intent.getStringExtra("Story");
        String Time = intent.getStringExtra("Time");
        String Imageg = intent.getStringExtra("Image");

        Glide.with(this).load(Imageg).into(RS_Image);




        RS_Written.setText("by  "+Written);
        RS_Title.setText(Title);
        RS_Story.setText(Story);
        RS_Type.setText(Time);
        RS_URL.setText(Imageg);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });











    }


}
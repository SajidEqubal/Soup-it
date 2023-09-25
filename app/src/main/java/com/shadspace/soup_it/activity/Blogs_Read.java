package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shadspace.soup_it.R;

public class Blogs_Read extends AppCompatActivity {


    ImageView RB_Image, Back;


    TextView RB_Category, RB_Title, RB_Written, RB_Date, RB_Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_blogs__read);


        RB_Image = findViewById(R.id.RB_Image);
        RB_Category = findViewById(R.id.RB_Category);
        RB_Title = findViewById(R.id.RB_Title);
        RB_Written = findViewById(R.id.RB_Written);
        RB_Date = findViewById(R.id.RB_Date);
        RB_Content = findViewById(R.id.RB_Content);
        Back = findViewById(R.id.Back);


        Intent intent = getIntent();
        String Category = intent.getStringExtra("Category");
        String Title = intent.getStringExtra("Title");
        String Content = intent.getStringExtra("Content");
        String Written = intent.getStringExtra("Written");
        String Time = intent.getStringExtra("Time");
        String Image = intent.getStringExtra("Image");

        Glide.with(this).load(Image).into(RB_Image);


        RB_Category.setText(Category);
        RB_Title.setText(Title);
        RB_Content.setText(Content);
        RB_Written.setText("by  " + Written);
        RB_Date.setText(Time);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }
}
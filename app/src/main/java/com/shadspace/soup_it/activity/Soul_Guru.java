package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shadspace.soup_it.R;

public class Soul_Guru extends AppCompatActivity {

    TextView COpriceOne,COprice3M,COprice6M,COprice12M;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_soul__guru);



        COpriceOne = findViewById(R.id.COprice1);
        COprice3M = findViewById(R.id.COprice3M);
        COprice6M = findViewById(R.id.COprice6M);
        COprice12M = findViewById(R.id.COprice12M);

       // COpriceOne.setText(someString); // SomeString = your old price
        COpriceOne.setPaintFlags(COpriceOne.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        COprice3M.setPaintFlags(COprice3M.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        COprice6M.setPaintFlags(COprice6M.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        COprice12M.setPaintFlags(COprice12M.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);




    }
}
package com.shadspace.soup_it.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.view.WindowManager;

import com.shadspace.soup_it.R;

import soup.neumorphism.NeumorphCardView;

public class NoInternet extends AppCompatActivity {

    NeumorphCardView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_no_internet);


        check = findViewById(R.id.Check);


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NoInternet.this,SplashScreen.class);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    public void onBackPressed() {

        finish();

    }
}
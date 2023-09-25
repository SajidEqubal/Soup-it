package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.shadspace.soup_it.R;

public class Share extends AppCompatActivity {

    ImageView Instagram,Facebook,Whats,More;
    String promo = "Download Soup-It App from Playstore & find your happy place !";
    String Link = "https://play.google.com/store/apps/details?id=com.shadspace.soup_it";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_share);


        Instagram = findViewById(R.id.Instagram);
        Facebook = findViewById(R.id.Facebook);
        Whats = findViewById(R.id.Whats);
        More = findViewById(R.id.More);





        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
               shareIntent.setType("text/plain");
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
                shareIntent.putExtra(Intent.EXTRA_TEXT,promo+" "+Link);
                shareIntent.setPackage("com.instagram.android");
                startActivity(shareIntent);

            }
        });


        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download Now");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, promo+" "+Link);
                startActivity(Intent.createChooser(sharingIntent, "Share text via"));




            }
        });


        Whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
                shareIntent.putExtra(Intent.EXTRA_TEXT,promo+" "+Link);
                shareIntent.setPackage("com.whatsapp");
                startActivity(shareIntent);

            }
        });


        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download Now");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, promo+" "+Link);
                startActivity(Intent.createChooser(sharingIntent, "Share text via"));
            }
        });
    }
}
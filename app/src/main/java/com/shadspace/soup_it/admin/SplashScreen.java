package com.shadspace.soup_it.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.OnBoardScreen.IntroActivity;
import com.shadspace.soup_it.R;

public class SplashScreen extends AppCompatActivity {


    ImageView imageView;
    Animation animFadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.splash_screen);


        imageView = findViewById(R.id.imageView);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);


        imageView.setVisibility(View.VISIBLE);
        imageView.startAnimation(animFadeIn);

        checkConnection();





    }


    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)

                this.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        NetworkInfo network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if (wifi.isConnected()) {
            //Internet available

            logedIn();

        } else if (network.isConnected()) {
            //Internet available

            logedIn();


        } else {
            //Internet is not available
            Intent intent = new Intent(SplashScreen.this, NoInternet.class);
            startActivity(intent);
            finish();
        }

    }



    private void logedIn() {
        if (SharedPerManager.getInstance(this).isLoggedIn()) {

            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, Home.class);
                    startActivity(intent);
                    finish();


                }
            }, 4000);

        } else {
            Intent intent = new Intent(SplashScreen.this, IntroActivity.class);
            startActivity(intent);
            finish();
        }

    }


}


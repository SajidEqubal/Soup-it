package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;
import com.google.android.play.core.tasks.Task;
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.admin.LoginNum;
import com.shadspace.soup_it.admin.SharedPerManager;

import soup.neumorphism.NeumorphTextView;

public class Profile extends AppCompatActivity {
    ImageView back;
    LinearLayout publishStory, joinUs, howWork, contactUs, viewSubmission, share, rate;
    NeumorphTextView ftr;

    TextView tv_Logout, ViewName;
    Animation BottomAnim;

    TextView version;




    Switch darkSwitch;
    LinearLayout drkMD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_profile);






        back = findViewById(R.id.back);
        tv_Logout = findViewById(R.id.tv_Logout);

        publishStory = findViewById(R.id.publishStory);
        joinUs = findViewById(R.id.joinUs);
        howWork = findViewById(R.id.howWork);
        contactUs = findViewById(R.id.contactUs);
        viewSubmission = findViewById(R.id.viewSubmission);
        share = findViewById(R.id.share);
        rate = findViewById(R.id.rate);
        drkMD = findViewById(R.id.drkMD);
        Switch aswitch = (Switch) findViewById(R.id.darkSwitch);

        version = findViewById(R.id.version);
        String v = getString(R.string.app_update);

        version.setText("Version "+v);


        //Show Names
        ViewName = findViewById(R.id.ViewName);
        ViewName.setText(SharedPerManager.getInstance(Profile.this).getUsername());


        ftr = findViewById(R.id.ftr);
        ftr.setPaintFlags(ftr.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        BottomAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

        // Animation In Feather Screen
        publishStory.setAnimation(BottomAnim);
        joinUs.setAnimation(BottomAnim);
        howWork.setAnimation(BottomAnim);
        contactUs.setAnimation(BottomAnim);
        viewSubmission.setAnimation(BottomAnim);
        share.setAnimation(BottomAnim);
        rate.setAnimation(BottomAnim);
        drkMD.setAnimation(BottomAnim);



        // Saving state of our app
        // using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
           // btnToggleDark.setText("Disable Dark Mode");
            aswitch.setChecked(true);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
           // btnToggleDark.setText("Enable Dark Mode");
            aswitch.setChecked(false);
        }






        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("isDarkModeOn", true);
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    // it will set isDarkModeOn
                    // boolean to false
                    editor.putBoolean("isDarkModeOn", false);
                    editor.apply();
                }
            }
        });








        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Profile.this, Home.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i2);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        publishStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Profile.this, PublishStory.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(publishStory, "PUB");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Profile.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        tv_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(Profile.this)
                        .setMessage("Are you sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPerManager.getInstance(Profile.this).logout();
                                Profile.this.finish();
                                startActivity(new Intent(Profile.this, LoginNum.class));

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


                //  SharedPerManager.getInstance(Profile.this).logout();
                //   Profile.this.finish();
                //   startActivity(new Intent(Profile.this, LoginNum.class));

            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ContactUs.class);
                startActivity(intent);
            }
        });

        joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, JoinUs.class);
                startActivity(intent);
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Share.class);
                startActivity(intent);
            }
        });


        howWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile.this, HowWork.class);
                startActivity(intent);


            }
        });

        viewSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile.this, ViewSubmission.class);
                startActivity(intent);


            }
        });





        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }




            }
        });







    }




    @Override
    public void onBackPressed() {

        Intent i2 = new Intent(Profile.this, Home.class);
        i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i2);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);


    }







}
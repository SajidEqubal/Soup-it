package com.shadspace.soup_it;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.mikelau.views.shimmer.ShimmerRecyclerViewX;
import com.shadspace.soup_it.Model.GoodNewsList;
import com.shadspace.soup_it.activity.Blogs;
import com.shadspace.soup_it.activity.Events;
import com.shadspace.soup_it.activity.Game;
import com.shadspace.soup_it.activity.Good_News;
import com.shadspace.soup_it.activity.PublishStory;
import com.shadspace.soup_it.activity.Quotes;
import com.shadspace.soup_it.activity.Short_Story;
import com.shadspace.soup_it.adapter.GoodNewsAdapter;
import com.shadspace.soup_it.admin.LoginNum;
import com.shadspace.soup_it.admin.SharedPerManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Home extends AppCompatActivity {


    //For Double Tap
    int i = 0;
    Dialog myDialog;


    private static final String goodnewsurl = "https://soupit.in/AppSoupit/GoodNews/json_user_fetch.php";
    ArrayList<GoodNewsList> goodNews = new ArrayList<GoodNewsList>();


    LinearLayout showAll;
    LinearLayout quotes, btn_blog, btn_story, btn_soul, btn_game, btn_write,btn_event;
    ImageView  signUp;
    LottieAnimationView Profile;

    private RecyclerView Rec_GoodNews;
    GoodNewsAdapter goodNewsAdapter;
    private RecyclerView.LayoutManager layoutManager1;

    String VersionUrl = "https://soupit.in/AppSoupit/Update/rtrve.php";
    TextView versionCheck, versionApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_home);


        //FOR POP-Up
        myDialog = new Dialog(this);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        if (!SharedPerManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginNum.class));
        }

        // Saving state of our app
        // using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            // btnToggleDark.setText("Disable Dark Mode");
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            // btnToggleDark.setText("Enable Dark Mode");
        }


        quotes = findViewById(R.id.quotes);
        Profile = findViewById(R.id.Profile);
        btn_blog = findViewById(R.id.btn_blog);
        btn_story = findViewById(R.id.btn_story);
        btn_soul = findViewById(R.id.btn_soul);
        btn_game = findViewById(R.id.btn_game);
        btn_event = findViewById(R.id.btn_event);
        btn_write = findViewById(R.id.btn_write);
        signUp = findViewById(R.id.signUp);


        showAll = findViewById(R.id.showAll);


        versionCheck = findViewById(R.id.versionCheck);
        versionApp = findViewById(R.id.versionApp);
        retrieveVersion();


        quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Home.this, Quotes.class);
                startActivity(i2);

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);


            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Home.this, com.shadspace.soup_it.activity.Profile.class);
                startActivity(intent);
                //   overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });


        btn_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Blogs.class);
                startActivity(intent);

            }
        });


        btn_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Short_Story.class);
                startActivity(intent);

            }
        });

        btn_soul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(Home.this, Soul_Guru.class);
                //  startActivity(intent);

                DoubleTap();

            }
        });


        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Game.class);
                startActivity(intent);

            }
        });

        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Events.class);
                startActivity(intent);

            }
        });



        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, PublishStory.class);
                startActivity(intent);

            }
        });


        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Good_News.class);
                startActivity(intent);

            }
        });


        ShimmerRecyclerViewX lv = findViewById(R.id.Rec_GoodNews);
        lv.showShimmerAdapter();

        Rec_GoodNews = findViewById(R.id.Rec_GoodNews);


        layoutManager1 = new LinearLayoutManager(this);
        Rec_GoodNews.setLayoutManager(layoutManager1);


        fetch_data_into_array(Rec_GoodNews);

    }

    private void checkUpdate() {

        String v = getString(R.string.app_update);


        if (versionCheck.getText().toString().equals(v)) {

            ShowUpdate();

        }
    }

    private void ShowUpdate() {

        TextView tv_nothnks;
        Button Update;
        myDialog.setContentView(R.layout.updatepopup);
        tv_nothnks = (TextView) myDialog.findViewById(R.id.tv_nothnks);
        Update = (Button) myDialog.findViewById(R.id.Update);
        Update.setBackgroundColor(Color.GREEN);
        tv_nothnks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }

        });

        Update.setOnClickListener(new View.OnClickListener() {
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


        myDialog.show();
    }


    private void retrieveVersion() {
        StringRequest request = new StringRequest(Request.Method.POST, VersionUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    String id = object.getString("id");
                                    String VersionData = object.getString("newVersion");


                                    versionCheck.setText(VersionData);

                                    checkUpdate();

                                }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }


    public void fetch_data_into_array(final View view) {

        class dbManager extends AsyncTask<String, Void, String> {
            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);


                        goodNews.add(new GoodNewsList
                                (jo.getString("category"),
                                        jo.getString("title"),
                                        jo.getString("content"),
                                        jo.getString("written"),
                                        jo.getString("time"),
                                        "https://soupit.in/AppSoupit/GoodNews/images/" + jo.getString("image")));

                        goodNewsAdapter = new GoodNewsAdapter(getApplicationContext(),
                                Home.this, goodNewsAdapter, goodNews);


                        Rec_GoodNews.setAdapter(goodNewsAdapter);

                        goodNewsAdapter.notifyDataSetChanged();


                    }


                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data = new StringBuffer();
                    String line;

                    while ((line = br.readLine()) != null) {
                        data.append(line + "\n");
                    }
                    br.close();

                    return data.toString();

                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }

        }
        dbManager obj = new dbManager();
        obj.execute(goodnewsurl);

    }


    private void DoubleTap() {

        i++;
        Handler handler = new Handler();
        Runnable r = new Runnable() {

            @Override
            public void run() {
                i = 0;
            }
        };

        if (i == 1) {
            //Single click
            ShowPopup();
            handler.postDelayed(r, 250);
        } else if (i == 2) {
            //Double click
            i = 0;
            Toast.makeText(Home.this, "Clicked", Toast.LENGTH_SHORT).show();
        }


    }


    // FOR Pop up
    public void ShowPopup() {
        TextView txtclose;
        LinearLayout LinearPop;
        myDialog.setContentView(R.layout.comingsoon_popup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        LinearPop = (LinearLayout) myDialog.findViewById(R.id.LinearPop);
        LinearPop.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }

        });


        myDialog.show();

    }  // its show the PopUp


}
package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
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
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.adapter.DataStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Quotes extends AppCompatActivity {

    ImageView back;
    LottieAnimationView refresh , share;
    LinearLayout quotes;


    String url = "https://soupit.in/AppSoupit/rtrve.php";
    String promo = "Download Soup-It App for daily quotes  ";
    String Link = "https://play.google.com/store/apps/details?id=com.shadspace.soup_it";


    TextView tv_quot, tv_written;
    //https://github.com/stfalcon-studio/swipeable-button
    //https://github.com/Krupen/FabulousFilter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_quotes);

        back = findViewById(R.id.back);
        tv_written = findViewById(R.id.tv_written);
        tv_quot = findViewById(R.id.tv_quot);
        refresh = findViewById(R.id.refresh);
        share = findViewById(R.id.share);
        quotes = findViewById(R.id.quotes);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Quotes.this, Home.class);
                startActivity(i2);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = tv_quot.getText().toString();
                String a = tv_written.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, s +"\n"+ a + "\n"+ "\n"+ promo + Link);
                startActivity(Intent.createChooser(sharingIntent, "Share text via"));
            }
        });












        retrieveData();
    }


    public void retrieveData() {
        refresh(10000);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        refresh.setVisibility(View.VISIBLE);


                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    String id = object.getString("id");
                                    String quot = object.getString("quot");
                                    String written = object.getString("written");


                                    //tvId.setText(id);
                                    tv_quot.setText(quot);
                                    tv_written.setText(written);


                                }

                                refresh.setVisibility(View.GONE);
                                quotes.setVisibility(View.VISIBLE);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Quotes.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    private void refresh(int millisecond) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                retrieveData();
            }
        };
        handler.postDelayed(runnable, millisecond);
    }
}
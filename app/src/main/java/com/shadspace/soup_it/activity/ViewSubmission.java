package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikelau.views.shimmer.ShimmerRecyclerViewX;
import com.shadspace.soup_it.Model.YourStoryList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.adapter.YourStoryAdapter;
import com.shadspace.soup_it.admin.SharedPerManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ViewSubmission extends AppCompatActivity {


    private static final String ReviewSubURL = "https://soupit.in/AppSoupit/YourStory/json_user_fetch.php";


    ArrayList<YourStoryList> yourStory = new ArrayList<YourStoryList>();

    LinearLayout StoryLayout, NoDataLayout;

    TextView StartWrite, tv_refresh, showData;


    private RecyclerView Rec_YourStory;
    YourStoryAdapter yourStoryAdapter;
    private RecyclerView.LayoutManager layoutManager1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_view_submission);


        ///https://www.geeksforgeeks.org/double-tap-on-a-button-in-android/


        ShimmerRecyclerViewX lv = findViewById(R.id.Rec_YourStory);
        lv.showShimmerAdapter();

        Rec_YourStory = findViewById(R.id.Rec_YourStory);
        showData = findViewById(R.id.showData);
        showData.setText("");


        StoryLayout = findViewById(R.id.StoryLayout);
        NoDataLayout = findViewById(R.id.NoDataLayout);
        StoryLayout.setVisibility(View.VISIBLE);
        NoDataLayout.setVisibility(View.GONE);

        StartWrite = findViewById(R.id.StartWrite);
        StartWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSubmission.this, PublishStory.class);
                startActivity(intent);
                finish();

            }
        });


        tv_refresh = findViewById(R.id.tv_refresh);
        tv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSubmission.this, ViewSubmission.class);
                startActivity(intent);
                finish();

            }
        });


        layoutManager1 = new LinearLayoutManager(this);
        Rec_YourStory.setLayoutManager(layoutManager1);


        fetch_data_into_array(Rec_YourStory);

          StopRefreshTwo();
    }




    public void fetch_data_into_array(final View view) {

        class dbManager extends AsyncTask<String, Void, String> {
            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);


                        if (SharedPerManager.getInstance(getApplicationContext()).getUserPhone().equals(jo.getString("phone"))) {


                            yourStory.add(new YourStoryList
                                    (jo.getString("title"),
                                            jo.getString("type"),
                                            jo.getString("story"),
                                            jo.getString("name"),
                                            jo.getString("phone"),
                                            jo.getString("date"),
                                            jo.getString("status")


                                    ));


                            showData.setText(jo.getString("title"));

                            if (showData.getText().toString().equals("")) {
                                Toast.makeText(ViewSubmission.this, "No Data ", Toast.LENGTH_SHORT).show();


                            } else {

                                StopRefresh();


                            }


                            yourStoryAdapter = new YourStoryAdapter(getApplicationContext(),
                                    ViewSubmission.this, yourStoryAdapter, yourStory);


                            Rec_YourStory.setAdapter(yourStoryAdapter);

                            yourStoryAdapter.notifyDataSetChanged();

                        }
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
        obj.execute(ReviewSubURL);

    }


    private void StopRefresh() {


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StoryLayout.setVisibility(View.VISIBLE);
                NoDataLayout.setVisibility(View.GONE);



            }
        }, 4000);


    }


    private void StopRefreshTwo() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NoDataLayout.setVisibility(View.VISIBLE);
                StoryLayout.setVisibility(View.GONE);

            }
        }, 4000);
    }


}











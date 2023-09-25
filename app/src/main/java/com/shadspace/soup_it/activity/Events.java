package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.mikelau.views.shimmer.ShimmerRecyclerViewX;
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.Model.EventList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.adapter.EventAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class Events extends AppCompatActivity {

    private static final String ReviewSubURL = "https://soupit.in/AppSoupit/Events/json_user_fetch.php";

    ArrayList<EventList> eventLists = new ArrayList<EventList>();


    private RecyclerView Rec_Events;

    EventAdapter eventAdapter;


    private RecyclerView.LayoutManager layoutManager, layoutManager1;
    NeumorphCardView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_events);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Events.this, Home.class);
                startActivity(intent);
                finish();

            }
        });


        ShimmerRecyclerViewX lv = findViewById(R.id.Rec_Events);
        lv.showShimmerAdapter();

        Rec_Events = findViewById(R.id.Rec_Events);


        layoutManager = new LinearLayoutManager(this);
        Rec_Events.setLayoutManager(layoutManager);


        Rec_Events.setLayoutManager(layoutManager);


        fetch_data_into_array(Rec_Events);


    }

    public void fetch_data_into_array(final View view) {

        class dbManager extends AsyncTask<String, Void, String> {
            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);


                        eventLists.add(new EventList
                                (jo.getString("title"),
                                        jo.getString("topic"),
                                        jo.getString("Sdate"),
                                        jo.getString("Ldate"),
                                        jo.getString("fee"),
                                        jo.getString("venue"),
                                        jo.getString("video"),
                                        jo.getString("availabe")


                                ));

                        eventAdapter = new EventAdapter(getApplicationContext(),
                                Events.this, eventAdapter, eventLists);


                        Rec_Events.setAdapter(eventAdapter);

                        eventAdapter.notifyDataSetChanged();


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
}
package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mikelau.views.shimmer.ShimmerRecyclerViewX;
import com.shadspace.soup_it.Model.StoryList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.adapter.StoryAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Short_Story extends AppCompatActivity {

    private static final String apiurl = "https://soupit.in/AppSoupit/Short_Story/json_user_fetch.php";


    ArrayList<StoryList> single = new ArrayList<StoryList>();
    TextView count, getloc;


    private RecyclerView Rec_Story;

    StoryAdapter myAdapter;


    private RecyclerView.LayoutManager layoutManager, layoutManager1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_short__story);





        getloc = findViewById(R.id.textView14);




        ShimmerRecyclerViewX lv = findViewById(R.id.Rec_Story);
        lv.showShimmerAdapter();

        Rec_Story = findViewById(R.id.Rec_Story);


        layoutManager = new LinearLayoutManager(this);
        Rec_Story.setLayoutManager(layoutManager);


        Rec_Story.setLayoutManager(layoutManager);



        fetch_data_into_array(Rec_Story);
    }


    public void fetch_data_into_array(final View view) {

        class dbManager extends AsyncTask<String, Void, String> {
            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);


                        single.add(new StoryList
                                        (jo.getString("written"),
                                        jo.getString("title"),
                                        jo.getString("story"),
                                        jo.getString("time"),
                                        "https://soupit.in/AppSoupit/Short_Story/images/" + jo.getString("image")));

                        myAdapter = new StoryAdapter(getApplicationContext(),
                                Short_Story.this, getloc.getText().toString(), myAdapter, single);


                        Rec_Story.setAdapter(myAdapter);

                        myAdapter.notifyDataSetChanged();


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
        obj.execute(apiurl);

    }
}
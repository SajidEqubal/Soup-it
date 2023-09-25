package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.mikelau.views.shimmer.ShimmerRecyclerViewX;
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.Model.GoodNewsList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.adapter.GoodNewsAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Good_News extends AppCompatActivity {


    EditText search;


    private static final String goodnewsurl = "https://soupit.in/AppSoupit/GoodNews/json_user_fetch.php";
    ArrayList<GoodNewsList> goodNews = new ArrayList<GoodNewsList>();


    private RecyclerView Rec_GoodNews;
    GoodNewsAdapter goodNewsAdapter;
    private RecyclerView.LayoutManager layoutManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_good__news);



        search = findViewById(R.id.Search);
        search.setText("");



        ShimmerRecyclerViewX lv = findViewById(R.id.Rec_GoodNews);
        lv.showShimmerAdapter();

        Rec_GoodNews = findViewById(R.id.Rec_GoodNews);


        layoutManager1 = new LinearLayoutManager(this);
        Rec_GoodNews.setLayoutManager(layoutManager1);


        fetch_data_into_array(Rec_GoodNews);
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
                                Good_News.this, goodNewsAdapter, goodNews);


                        Rec_GoodNews.setAdapter(goodNewsAdapter);

                        goodNewsAdapter.notifyDataSetChanged();


                    }


                    search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            filter(s.toString());

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });


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


    void filter(String text) {
        ArrayList<GoodNewsList> temp = new ArrayList();
        for (GoodNewsList d : goodNews) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getTtl3().toLowerCase().contains(text.toLowerCase())) {
                temp.add(d);
            }
        }
        //update recyclerview
        goodNewsAdapter.updateList(temp);

    }
}
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

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikelau.views.shimmer.ShimmerRecyclerViewX;
import com.shadspace.soup_it.Model.BlogList;
import com.shadspace.soup_it.Model.PopularBlogList;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.adapter.BlogAdapter;
import com.shadspace.soup_it.adapter.PopularBlogAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Blogs extends AppCompatActivity {


    private static final String apiurl = "https://soupit.in/AppSoupit/Blogs/json_user_fetch.php";

    private static final String apiurl2 = "https://soupit.in/AppSoupit/Popular_Blogs/json_user_fetch.php";


    ArrayList<BlogList> blog = new ArrayList<BlogList>();
    ArrayList<PopularBlogList> PersonalBlog = new ArrayList<PopularBlogList>();

    TextView count, getloc;


    private RecyclerView Rec_Blog;
    private RecyclerView Rec_PopularBlog;

    BlogAdapter myAdapter1;
    PopularBlogAdapter popularBlogAdapter;


    private RecyclerView.LayoutManager layoutManager1;

    private RecyclerView.LayoutManager layoutManager2;

    ImageView Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_blogs);


        getloc = findViewById(R.id.getloc);


        Profile = findViewById(R.id.Profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Blogs.this, com.shadspace.soup_it.activity.Profile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });




        ShimmerRecyclerViewX lv = findViewById(R.id.Rec_Blog);
        lv.showShimmerAdapter();

        ShimmerRecyclerViewX lv1 = findViewById(R.id.Rec_PopularBlog);
        lv1.showShimmerAdapter();

        Rec_Blog = findViewById(R.id.Rec_Blog);
        Rec_PopularBlog = findViewById(R.id.Rec_PopularBlog);


        layoutManager1 = new LinearLayoutManager(this);
        Rec_Blog.setLayoutManager(layoutManager1);

        layoutManager2 = new LinearLayoutManager(this);
     //   Rec_Blog.setLayoutManager(layoutManager2);


        Rec_Blog.setLayoutManager(layoutManager1);
      //  Rec_PopularBlog.setLayoutManager(layoutManager2);

        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        Rec_PopularBlog.setLayoutManager(layoutManager2);

        fetch_data_into_array(Rec_Blog);
        fetch_data_into_array2(Rec_PopularBlog);






    }

    public void fetch_data_into_array(final View view) {

        class dbManager extends AsyncTask<String, Void, String> {
            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);


                        blog.add(new BlogList
                                (jo.getString("category"),
                                        jo.getString("title"),
                                        jo.getString("content"),
                                        jo.getString("written"),
                                        jo.getString("time"),
                                        "https://soupit.in/AppSoupit/Blogs/images/" + jo.getString("image")));

                        myAdapter1 = new BlogAdapter(getApplicationContext(),
                                Blogs.this, getloc.getText().toString(), myAdapter1, blog);


                        Rec_Blog.setAdapter(myAdapter1);

                        myAdapter1.notifyDataSetChanged();


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


    public void fetch_data_into_array2(final View view) {

        class dbManager extends AsyncTask<String, Void, String> {
            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);


                        PersonalBlog.add(new PopularBlogList
                                (jo.getString("category"),
                                        jo.getString("title"),
                                        jo.getString("content"),
                                        jo.getString("written"),
                                        jo.getString("time"),
                                        "https://soupit.in/AppSoupit/Popular_Blogs/images/" + jo.getString("image")));

                        popularBlogAdapter = new PopularBlogAdapter(getApplicationContext(),
                                Blogs.this, popularBlogAdapter, PersonalBlog);


                        Rec_PopularBlog.setAdapter(popularBlogAdapter);

                        popularBlogAdapter.notifyDataSetChanged();


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
        obj.execute(apiurl2);

    }
}
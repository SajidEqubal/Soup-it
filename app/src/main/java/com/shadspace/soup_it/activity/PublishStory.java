package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.admin.SharedPerManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class PublishStory extends AppCompatActivity {

    String url = "https://soupit.in/AppSoupit/Publish_Story/insert.php";

    NeumorphButton review, next;
    NeumorphCardView back;


    TextInputLayout selectType;
    AutoCompleteTextView actType;

    EditText editTextTitle, editTextStory,editTextStatus;

    ArrayList<String> arrayListType;
    ArrayAdapter<String> arrayAdapterType;


    LinearLayout layTitle, layType, layStory, Loading;
    TextView tv_empty;

    int i = 0;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_publish_story);

        //FOR POP-Up
        myDialog = new Dialog(this);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));




        selectType = findViewById(R.id.selectType);
        actType = findViewById(R.id.actType);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextStory = findViewById(R.id.editTextStory);
        editTextStatus = findViewById(R.id.editTextStatus);
        editTextStatus.setText("Under Review");


        layTitle = findViewById(R.id.layTitle);
        layType = findViewById(R.id.layType);
        layStory = findViewById(R.id.layStory);
        tv_empty = findViewById(R.id.tv_empty);


        Loading = findViewById(R.id.Loading);

        //   layTitle.setVisibility(View.VISIBLE);
        //  layType.setVisibility(View.GONE);
        //  layStory.setVisibility(View.GONE);


        review = findViewById(R.id.review);
        next = findViewById(R.id.next);
        back = findViewById(R.id.back);





        arrayListType = new ArrayList<>();
        arrayListType.add("Social");
        arrayListType.add("Comedy");
        arrayListType.add("Peace");
        arrayListType.add("Mind");
        arrayListType.add("Relax");

        arrayAdapterType = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_menu, arrayListType);
        actType.setAdapter(arrayAdapterType);
        actType.setThreshold(1);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editTextTitle.getText().toString().equals("")) {
                    tv_empty.setVisibility(View.VISIBLE);

                }

                else if (i == 0) {


                    layTitle.animate()

                            .translationY(layTitle.getHeight())
                            .alpha(0.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    layTitle.setVisibility(View.GONE);


                                }
                            });

                    layType.animate()

                            .translationY(layType.getHeight())
                            .alpha(1.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    layType.setVisibility(View.VISIBLE);


                                }
                            });

                    i++;

                } else if (i == 1) {

                    next.setVisibility(View.GONE);
                    review.setVisibility(View.VISIBLE);


                    layType.animate()

                            .translationY(layType.getHeight())
                            .alpha(0.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    layType.setVisibility(View.GONE);


                                }
                            });

                    layStory.animate()

                            .translationY(layStory.getHeight())
                            .alpha(1.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    layStory.setVisibility(View.VISIBLE);


                                }
                            });

                    i = 0;


                }


            }







        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublishStory.this, Home.class);
                startActivity(intent);
                finish();

            }
        });


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitStory();


            }
        });


    }


    // FOR Pop up
    public void ShowPopup() {
        TextView txtclose,homeBack;
        LinearLayout LinearPop;
        Button btnViewSubmission;
        myDialog.setContentView(R.layout.custompopup);
        homeBack = (TextView) myDialog.findViewById(R.id.homeBack);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        LinearPop = (LinearLayout) myDialog.findViewById(R.id.LinearPop);
        btnViewSubmission = (Button) myDialog.findViewById(R.id.btnViewSubmission);
        LinearPop.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // myDialog.dismiss();
                Intent intent = new Intent(PublishStory.this,Home.class);
                startActivity(intent);
                finish();
            }

        });
        btnViewSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublishStory.this, ViewSubmission.class);
                startActivity(intent);
                finish();
            }
        });


        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublishStory.this,Home.class);
                startActivity(intent);
                finish();
            }
        });


       myDialog.show();

    }  // its show the PopUp


    public void submitStory() {

        Loading.setVisibility(View.VISIBLE);
        layTitle.setVisibility(View.GONE);
        layType.setVisibility(View.GONE);
        layStory.setVisibility(View.GONE);


        String stringTitle = editTextTitle.getText().toString();
        String stringType = arrayAdapterType.toString();
        String stringStory = editTextStory.getText().toString();
        String stringStatus = editTextStatus.getText().toString();

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Data Inserted")) {

                    Loading.setVisibility(View.GONE);
                    layStory.setVisibility(View.VISIBLE);

                   // Intent intent = new Intent(PublishStory.this,ReviewSubmission.class);
                   // startActivity(intent);

                    ShowPopup();

                    clearData();

                } else {

                    Toast.makeText(getApplicationContext(), "Please try after sometime", Toast.LENGTH_SHORT).show();
                    Loading.setVisibility(View.VISIBLE);

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  load.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                params.put("title", stringTitle);
                params.put("type", stringType);
                params.put("story", stringStory);
                params.put("name", SharedPerManager.getInstance(getBaseContext()).getUsername() );
                params.put("phone",  SharedPerManager.getInstance(getBaseContext()).getUserPhone());
                params.put("date", currentDateTimeString);
                params.put("status", stringStatus);
                return params;

              //  SharedPerManager.getInstance(getBaseContext()).getUserEmail()
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }

    private void clearData() {

        editTextTitle.setText("");
        editTextStory.setText("");
    }
}
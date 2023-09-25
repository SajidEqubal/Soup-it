package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.admin.SharedPerManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import soup.neumorphism.NeumorphCardView;

public class EventsDetails extends AppCompatActivity {

    Button btn_register;
    Dialog myDialog;
    TextView ED_Title, ED_Topic, Last_Reg_Date, Start_date, End_Date, Fee, Venue, Video,ED_Available;

    String url = "https://soupit.in/AppSoupit/Events/RegEvent/insert.php";
    LinearLayout Loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_events_details);


        ED_Title = findViewById(R.id.ED_Title);
        ED_Topic = findViewById(R.id.ED_Topic);
        Last_Reg_Date = findViewById(R.id.Last_Reg_Date);
        Start_date = findViewById(R.id.Start_date);
        End_Date = findViewById(R.id.End_Date);
        Fee = findViewById(R.id.Fee);
        Venue = findViewById(R.id.Venue);
        Video = findViewById(R.id.Video);
        ED_Available = findViewById(R.id.ED_Available);

        Loading = findViewById(R.id.Loading);




        Intent intent = getIntent();
        String TITLE = intent.getStringExtra("Title");
        String TOPIC = intent.getStringExtra("Topic");
        String SDATE = intent.getStringExtra("Sdate");
        String LDATE = intent.getStringExtra("Ldate");
        String FEE = intent.getStringExtra("Fee");
        String VENUE = intent.getStringExtra("Venue");
        String VIDEO = intent.getStringExtra("Video");
        String AVAILABLE = intent.getStringExtra("Available");


        ED_Title.setText(TITLE);
        ED_Topic.setText("TOPIC •  "+TOPIC);
        Last_Reg_Date.setText(LDATE);
        Start_date.setText(SDATE);
        End_Date.setText(LDATE);
        Fee.setText(FEE);
        Venue.setText(VENUE);
        Video.setText(VIDEO);
        ED_Available.setText(AVAILABLE);


        btn_register = findViewById(R.id.btn_register);

        myDialog = new Dialog(this);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        CheckAvailableEvents();


    }

    private void CheckAvailableEvents() {
        if (ED_Available.getText().toString().equals("open"))
        {
            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegisterEvent();
                }
            });
        }
        else {
            btn_register.setEnabled(false);
            btn_register.setBackgroundColor(Color.parseColor("#808080"));
            btn_register.setText("REGISTRATION CLOSED");
        }


    }

    private void RegisterEvent() {
        Loading.setVisibility(View.VISIBLE);

        String stringTitle = ED_Title.getText().toString();
        String stringSdate = Start_date.getText().toString();

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Data Inserted")) {
                    Loading.setVisibility(View.GONE);
                    ShowPopup();


                } else {

                    Toast.makeText(getApplicationContext(), "Please try after sometime", Toast.LENGTH_SHORT).show();
                    Loading.setVisibility(View.VISIBLE);

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Loading.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                params.put("name", SharedPerManager.getInstance(getBaseContext()).getUsername());
                params.put("phone", SharedPerManager.getInstance(getBaseContext()).getUserPhone());
                params.put("gmail", SharedPerManager.getInstance(getBaseContext()).getUserEmail());
                params.put("eventName", stringTitle );
                params.put("eventDate",  stringSdate);
                params.put("regDateTime", currentDateTimeString);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);



    }


    // FOR Pop up
    public void ShowPopup() {
        TextView txtclose;
        Button done;
        myDialog.setContentView(R.layout.register_sucess_popup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        done = (Button) myDialog.findViewById(R.id.done);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }

        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Intent intent = new Intent(EventsDetails.this,Home.class);
                startActivity(intent);
                finish();

            }

        });


        myDialog.show();

    }  // its show the PopUp
}
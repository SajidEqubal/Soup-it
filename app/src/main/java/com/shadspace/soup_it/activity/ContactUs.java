package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
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
import com.google.android.material.textfield.TextInputEditText;
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.admin.SharedPerManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import soup.neumorphism.NeumorphButton;

public class ContactUs extends AppCompatActivity {
    String url = "https://soupit.in/AppSoupit/ContactUs/insert.php";
    TextInputEditText editTextUsername, editTextEmail, editTextMessage;
    NeumorphButton sendMessage;

    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_contact_us);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMessage = findViewById(R.id.editTextMessage);
        sendMessage = findViewById(R.id.sendMessage);


        //FOR POP-Up
        myDialog = new Dialog(this);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }


    public void sendMessage() {

        String stringName = editTextUsername.getText().toString();
        String stringEmail = editTextEmail.getText().toString();
        String stringMessage = editTextMessage.getText().toString();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Data Inserted")) {

                 //   Toast.makeText(ContactUs.this, "data sent", Toast.LENGTH_SHORT).show();

                    ShowPopup();
                    clearData();

                } else {

                    Toast.makeText(getApplicationContext(), "Message Sent Failed, Please Try Again", Toast.LENGTH_SHORT).show();


                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                params.put("name", stringName);
                params.put("email", stringEmail);
                params.put("phone", SharedPerManager.getInstance(getBaseContext()).getUserPhone());
                params.put("message", stringMessage);
                params.put("date", currentDateTimeString);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }

    private void clearData() {
        editTextUsername.setText("");
        editTextEmail.setText("");
        editTextMessage.setText("");
    }


    // FOR Pop up
    public void ShowPopup() {
        TextView txtclose;
        Button btnHome;
        LinearLayout contactPop;
        myDialog.setContentView(R.layout.contactpopup);
        contactPop = (LinearLayout) myDialog.findViewById(R.id.contactPop);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        btnHome = (Button) myDialog.findViewById(R.id.btnHome);
        contactPop.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUs.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        myDialog.show();

    }


}
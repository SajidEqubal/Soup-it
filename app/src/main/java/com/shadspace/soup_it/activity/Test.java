package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shadspace.soup_it.R;
import com.shadspace.soup_it.admin.SharedPerManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

 //   public int counter;
  //  NeumorphTextView OtpTimer;

   // public static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    String url = "https://soupit.in/AppSoupit/YourStory/StopRefreshRecycler_retrieve.php";


    TextView idd, titlee, typee, storyy,namee, phonee, datee, statuss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        idd = findViewById(R.id.id);
        titlee = findViewById(R.id.title);
        typee = findViewById(R.id.type);
        storyy = findViewById(R.id.story);
        namee = findViewById(R.id.name);
        phonee = findViewById(R.id.phone);
        datee = findViewById(R.id.date);
        statuss = findViewById(R.id.status);



        StopRefreshRecycler_retrieve();

     //   OtpTimer = findViewById(R.id.OtpTimer);




      //  new CountDownTimer(30000, 1000){
     //       public void onTick(long millisUntilFinished){
     //           OtpTimer.setText("Resend in "+ String.valueOf(counter)+" sec");
     //           counter++;
     //       }
     //       public  void onFinish(){
     //           OtpTimer.setText("Click me to Resend");
     //           OtpTimer.setTextColor(Color.BLACK);
//
      //      }
     //   }.start();



  /**      new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                OtpTimer.setText("Resend in " + millisUntilFinished / 1000+" sec");
            }

            public void onFinish() {
                OtpTimer.setText("RESEND OTP");
                OtpTimer.setTextColor(Color.BLACK);
                OtpTimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Test.this,Test.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();  */



    }

    public void StopRefreshRecycler_retrieve() {

        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();


        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // employeeArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    if (SharedPerManager.getInstance(getApplicationContext()).getUserPhone().equals(object.getString("phone"))) {
                                        String id = object.getString("id");
                                        String title = object.getString("title");
                                        String type = object.getString("type");
                                        String story = object.getString("story");
                                        String name = object.getString("name");
                                        String phone = object.getString("phone");
                                        String date = object.getString("date");
                                        String status = object.getString("status");







                                        idd.setText(id);
                                        titlee.setText(title);
                                        typee.setText(type);
                                        storyy.setText(story);
                                        namee.setText(name);
                                        phonee.setText(phone);
                                        datee.setText(date);
                                        statuss.setText(status);






                                        if (title.trim().equals("")) {
                                            Toast.makeText(Test.this, "Empty", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(Test.this, "Data available", Toast.LENGTH_SHORT).show();

                                        }

                                    }


                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Test.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}
package com.shadspace.soup_it.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphTextView;

public class LoginNum extends AppCompatActivity {


    private TextInputEditText editTextEmail, editTextPassword;
    NeumorphButton buttonLogin;


    int otp = 0;

    public int counter;

    String username, emailID, phone;

    Button verify;
    NeumorphTextView otpHead, OtpTimer;
    TextInputEditText o1, o2, o3, o4, o5;
    LinearLayout check, create;
    LottieAnimationView load;
    TextView tv_check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_login_num);


        load = findViewById(R.id.load);
        verify = findViewById(R.id.verify);
        otpHead = findViewById(R.id.otpHead);
        o1 = findViewById(R.id.o1);
        o2 = findViewById(R.id.o2);
        o3 = findViewById(R.id.o3);
        o4 = findViewById(R.id.o4);
        o5 = findViewById(R.id.o5);
        check = findViewById(R.id.check);
        create = findViewById(R.id.create);

        OtpTimer = findViewById(R.id.OtpTimer);
        tv_check = findViewById(R.id.tv_check);


        editTextEmail = findViewById(R.id.editTextEmail);
        //  editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);


        check.setVisibility(View.GONE);
        create.setVisibility(View.VISIBLE);


        ActivityCompat.requestPermissions(LoginNum.this, new String[]{android.Manifest.permission.RECEIVE_SMS},
                1);


        verify.setEnabled(false);
        // buttonLogin.setEnabled(false);


        otp = gen();

        o1.setEnabled(true);
        o2.setEnabled(false);
        o3.setEnabled(false);
        o4.setEnabled(false);
        o5.setEnabled(false);


        o1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (o1.getText().toString().length() == 1) {
                    o2.setEnabled(true);
                    o2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        o2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (o1.getText().toString().length() == 1 && o2.getText().toString().length() == 1) {
                    o3.setEnabled(true);
                    o3.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        o3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (o1.getText().toString().length() == 1 && o2.getText().toString().length() == 1
                        && o3.getText().toString().length() == 1) {
                    o4.setEnabled(true);
                    o4.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        o4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (o1.getText().toString().length() == 1 && o2.getText().toString().length() == 1
                        && o3.getText().toString().length() == 1 && o4.getText().toString().length() == 1) {
                    o5.setEnabled(true);
                    o5.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        o5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (o1.getText().toString().length() == 1 && o2.getText().toString().length() == 1
                        && o3.getText().toString().length() == 1 && o4.getText().toString().length() == 1 &&
                        o5.getText().toString().length() == 1) {
                    o5.setEnabled(true);


                    verify.setEnabled(true);

                } else {
                    verify.setEnabled(false);


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String entered;

                entered = o1.getText().toString() + o2.getText().toString() + o3.getText().toString() +
                        o4.getText().toString() + o5.getText().toString();


                if (entered.equals(String.valueOf(otp))) {


                    SharedPerManager.getInstance(getApplicationContext())
                            .userLogin(
                                    username,
                                    emailID,
                                    phone
                            );

                    startActivity(new Intent(getApplicationContext(), Home.class));
                    finish();
                } else {
                    Toast.makeText(LoginNum.this, "Wrong OTP, Please Check OTP", Toast.LENGTH_SHORT).show();
                }


            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneNumberCheck();

            }
        });


    }

    private void PhoneNumberCheck() {


        if (editTextEmail.getText().toString().length() == 10) {
            tv_check.setVisibility(View.GONE);
            userLogin();

        } else {
            tv_check.setVisibility(View.VISIBLE);

            editTextEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (editTextEmail.getText().toString().length() == 10) {
                        tv_check.setVisibility(View.GONE);

                    } else {
                        tv_check.setVisibility(View.VISIBLE);

                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }


    }


    public void userLogin() {

        load.setVisibility(View.VISIBLE);


        final String[] email = {editTextEmail.getText().toString().trim()};

        String PhoneNo = editTextEmail.getText().toString();


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        load.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {

                                if (obj.getString("phone").equals(editTextEmail.getText().toString())) {

                                    create.setVisibility(View.GONE);
                                    check.setVisibility(View.VISIBLE);

                                    ResendOtp();

                                    username = obj.getString("username");
                                    emailID = obj.getString("email");
                                    phone = obj.getString("phone");

                                    sendSms(username, phone);

                                } else {
                                    // Toast.makeText(getApplicationContext(),"Not",Toast.LENGTH_SHORT).show();

                                }
                            } else {


                                Intent intent = new Intent(getBaseContext(), Signup.class);
                                intent.putExtra("emailAsPhoneNumber", PhoneNo);
                                startActivity(intent);
                                finish();


                                // startActivity(new Intent(getApplicationContext(), Signup.class));
                                // finish();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        load.setVisibility(View.GONE);

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone", email[0]);
                //params.put("password", password);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }


    public void sendSms(String message, String number) {
        try {
            // String apiKey="F7kirHDz8jMrptMfuaezkBtloFrMNgnjGndNVRF9KBFy08j52E5XUpwWgPkL";
            String apiKey = "J7Ggs2L1C5wMzZjqOQcbRxAhFlkfNKpu8XYEm4TVdIUyoWHrveNJw5ckXiuULsqYGAVDtZS7avy1OHmo";
            message = URLEncoder.encode(message, "UTF-8");


            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&variables_values=" + otp + "&route=otp&numbers=" + number;

            Log.d("otp---", myUrl);



            RequestQueue queue = Volley.newRequestQueue(this);
            String url = myUrl;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // process your response here

                            otpHead.setText("OTP sent to " + editTextEmail.getText().toString());

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //perform operation here after getting error
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(stringRequest);


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public int gen() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }


    private void ResendOtp() {

        new CountDownTimer(40000, 1000) {

            public void onTick(long millisUntilFinished) {
                OtpTimer.setText("Resend in " + millisUntilFinished / 1000 + " sec");
            }

            public void onFinish() {
                OtpTimer.setText("RESEND OTP");
                OtpTimer.setTextColor(Color.BLACK);
                OtpTimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginNum.this, LoginNum.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();
    }


}

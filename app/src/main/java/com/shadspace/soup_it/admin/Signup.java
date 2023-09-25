package com.shadspace.soup_it.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.shadspace.soup_it.Home;
import com.shadspace.soup_it.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import soup.neumorphism.NeumorphTextView;

public class Signup extends AppCompatActivity implements View.OnClickListener {


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //  "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    //  "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    EditText editTextUsername, editTextEmail, editTextPassword, editTextPhone;
    private Button buttonRegister, verify;
    private TextView textViewLogin;
    LinearLayout check, create;
    EditText editDate;


    NeumorphTextView otpHead, OtpTimer;
    EditText o1, o2, o3, o4, o5;
    int otp = 0;

    LottieAnimationView load;

    TextView tv_tC, tv_agree;
    CheckBox checkBox;

    Animation btnAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_signup);


        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        load = findViewById(R.id.load);
        tv_tC = findViewById(R.id.tv_tC);
        tv_agree = findViewById(R.id.tv_agree);
        OtpTimer = findViewById(R.id.OtpTimer);
        checkBox = findViewById(R.id.checkbox);


        tv_tC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Signup.this, "T&C", Toast.LENGTH_SHORT).show();
            }
        });


        // For checkbox checked
        checkBox.setChecked(true);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Toast.makeText(Signup.this, "Checkbox is Checked", Toast.LENGTH_SHORT).show();
                    tv_agree.setVisibility(View.GONE);
                    buttonRegister.animate()

                            .translationY(buttonRegister.getHeight())
                            .alpha(1.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    buttonRegister.setVisibility(View.VISIBLE);


                                }
                            });

                } else {
                    tv_agree.setVisibility(View.VISIBLE);
                    buttonRegister.animate()

                            .translationY(buttonRegister.getHeight())
                            .alpha(0.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    buttonRegister.setVisibility(View.GONE);


                                }
                            });
                }
            }
        });


        otp = gen();
        init();

        check.setVisibility(View.GONE);
        create.setVisibility(View.VISIBLE);


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


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String CheckName = editTextUsername.getText().toString();
                String CheckEmail = editTextEmail.getText().toString();
                String CheckPhone = editTextPhone.getText().toString();

                if (CheckName.trim().equals("")) {
                    Toast.makeText(Signup.this, "Please Check Name", Toast.LENGTH_SHORT).show();
                } else if (CheckEmail.trim().equals("")) {
                    Toast.makeText(Signup.this, "Please Check Email", Toast.LENGTH_SHORT).show();

                } else if (CheckPhone.trim().equals("")) {
                    Toast.makeText(Signup.this, "Please Check Phone Number", Toast.LENGTH_SHORT).show();

                } else if (!(CheckPhone.length() == 10)) {
                    Toast.makeText(Signup.this, "Please Enter 10 Digit Phone Number", Toast.LENGTH_SHORT).show();
                } else {

                    RegisterAccount();


                    sendSms(editTextUsername.getText().toString(), editTextPhone.getText().toString());
                    create.setVisibility(View.GONE);
                    check.setVisibility(View.VISIBLE);

                    ResendOtp();

                }


            }
        });


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String entered;

                entered = o1.getText().toString() + o2.getText().toString() + o3.getText().toString() +
                        o4.getText().toString() + o5.getText().toString();


                if (entered.equals(String.valueOf(otp))) {
                    registerUser();
                    //Toast.makeText(getApplicationContext(),"register",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signup.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void RegisterAccount() {
    }


    private boolean validateEmail() {
        final String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email address");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        final String username = editTextUsername.getText().toString().trim();

        if (username.isEmpty()) {
            editTextUsername.setError("Field can't be empty");
            return false;
        } else if (username.length() > 15) {
            editTextUsername.setError("Username too long");
            return false;
        } else {
            editTextUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        final String password = editTextPassword.getText().toString().trim();

        if (password.isEmpty()) {
            editTextPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            editTextPassword.setError("Password too weak");
            return false;
        } else {
            editTextPassword.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        final String password = editTextPhone.getText().toString().trim();

        if (password.isEmpty()) {
            editTextPhone.setError("Field can't be empty");
            return false;
        } else if (!Patterns.PHONE.matcher(password).matches()) {
            editTextPhone.setError("Incorrect Phone Number");
            return false;
        } else {
            editTextPhone.setError(null);
            return true;
        }
    }


    public void registerUser() {
        if (!validateEmail() | !validateUsername() | !validatePassword() | !validatePhone()) {
            return;
        }
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        load.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        load.setVisibility(View.GONE);

                        SharedPerManager.getInstance(getApplicationContext())
                                .userLogin(
                                        editTextUsername.getText().toString(),
                                        editTextEmail.getText().toString(),
                                        editTextPhone.getText().toString()

                                );

                        editTextUsername.setText("");
                        editTextEmail.setText("");
                        editTextPassword.setText("");
                        editTextPhone.setText("");
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();


                        //We will open directly the home screen


                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        load.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);
                params.put("date", currentDateTimeString);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onClick(View view) {

        if (view == textViewLogin)
            startActivity(new Intent(this, LoginNum.class));
    }

    public int gen() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
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

                            otpHead.setText("OTP sent to " + editTextPhone.getText().toString());

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

    public void init() {
        verify = findViewById(R.id.verify);
        otpHead = findViewById(R.id.otpHead);
        o1 = findViewById(R.id.o1);
        o2 = findViewById(R.id.o2);
        o3 = findViewById(R.id.o3);
        o4 = findViewById(R.id.o4);
        o5 = findViewById(R.id.o5);
        check = findViewById(R.id.check);
        create = findViewById(R.id.create);

        editDate = findViewById(R.id.editDate);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        editTextPassword.setText("Free Account");

        Intent intent = getIntent();
        String PhoneNo = intent.getStringExtra("emailAsPhoneNumber");
        editTextPhone.setText(PhoneNo);


        buttonRegister = (Button) findViewById(R.id.buttonRegister);

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
                        Intent intent = new Intent(Signup.this, Signup.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();
    }


}

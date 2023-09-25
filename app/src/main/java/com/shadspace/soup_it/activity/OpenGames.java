package com.shadspace.soup_it.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.airbnb.lottie.LottieAnimationView;
import com.shadspace.soup_it.R;

public class OpenGames extends AppCompatActivity {
    private WebView webView;
    LottieAnimationView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_open_games);


        //Receve Data
        Intent intent = getIntent();
        String Url1 = intent.getStringExtra("url1");


        webView = (WebView) findViewById(R.id.webview);
        progressBar = (LottieAnimationView) findViewById(R.id.progressBar);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(Url1);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            //this function start or finish the page functions...
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //set progress bar when page loading start...
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading...Please Wait");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //in this function visible the bar
                progressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());
                super.onPageFinished(view, url);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.canGoBack();
        }else {
            super.onBackPressed();
        }
    }
}



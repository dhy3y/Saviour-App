package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DonationRequests extends AppCompatActivity {

    String link;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_requests);

        //https://docs.google.com/spreadsheets/d/17UOhoISn3nIlu1JBy19edzIgVBU3QHd0nLqPzGC__Mc/edit?usp=sharing

        mWebView = (WebView) findViewById(R.id.webviewdonreq);
        loadLink();
    }

    public void loadLink() {
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://docs.google.com/spreadsheets/d/17UOhoISn3nIlu1JBy19edzIgVBU3QHd0nLqPzGC__Mc/edit?usp=sharing");
    }
}

package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Donation extends AppCompatActivity {

    String link;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        //https://docs.google.com/forms/d/e/1FAIpQLSc4bJE7wWIQjDdd1XZrWA_1fASeoffaSM3OxoSEs5ssIXpEQw/viewform

        mWebView = (WebView) findViewById(R.id.webviewdon);
        loadLink();
    }

    public void loadLink() {
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSc4bJE7wWIQjDdd1XZrWA_1fASeoffaSM3OxoSEs5ssIXpEQw/viewform");
    }
}

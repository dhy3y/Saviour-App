package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CoronaInfo extends AppCompatActivity {

    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_info);

        //https://web-chat.global.assistant.watson.cloud.ibm.com/preview.html?region=eu-gb&integrationID=24784550-02f5-4cb0-a326-fb77ca45494c&serviceInstanceID=5fba1b72-e46f-40fd-8fb6-7aed8f1f48a7

        mWebView = (WebView) findViewById(R.id.webviewCoronaInfo);
        loadLink();
    }

    public void loadLink() {

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://web-chat.global.assistant.watson.cloud.ibm.com/preview.html?region=eu-gb&integrationID=24784550-02f5-4cb0-a326-fb77ca45494c&serviceInstanceID=5fba1b72-e46f-40fd-8fb6-7aed8f1f48a7");
    }
}

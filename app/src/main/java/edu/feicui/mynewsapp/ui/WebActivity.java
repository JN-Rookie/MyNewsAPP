package edu.feicui.mynewsapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import edu.feicui.mynewsapp.R;

public class WebActivity extends AppCompatActivity {
    private WebView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
        mView= (WebView) findViewById(R.id.webview);
        WebSettings settings=mView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        mView.loadUrl(url);

    }
}

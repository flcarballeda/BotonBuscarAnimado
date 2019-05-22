package com.example.intentimplicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;

public class WebActivity extends AppCompatActivity {
    public static final String INTENT_PARAMETER = "WebView_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        String text =intent.getStringExtra( WebActivity.INTENT_PARAMETER);
        if (null == text) {
            text = "https://dentaris-sa.com/";
        }
        try {
            new java.net.URL( text);
            WebView webView = findViewById( R.id.webView);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webView.setWebViewClient(new WebViewClient() {

                //este método se llama cada vez que cambiamos de página
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Log.d("MIAPP", "Vistando ... " + url);
                    view.loadUrl(url);
                    return true;
                }
            });
            webView.loadUrl( text);
        } catch (MalformedURLException ex) {
            this.finish();
        }
    }
}

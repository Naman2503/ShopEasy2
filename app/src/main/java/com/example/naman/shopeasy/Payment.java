package com.example.naman.shopeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Payment extends AppCompatActivity {
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        wv= (WebView) findViewById(R.id.wv1);
        wv.loadUrl("https://paytm.com");
    }
}

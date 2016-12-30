package com.pranay.fabrictools.mopub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mopub.mobileads.MoPubView;

import io.fabric.sdk.android.Fabric;

public class BannerAdActivity extends AppCompatActivity {
    private MoPubView moPubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this);
        setContentView(R.layout.activity_banner_ad);


        moPubView = (MoPubView) findViewById(R.id.mopub_sample_ad);
        // TODO: Replace this test id with your personal ad unit id
        moPubView.setAdUnitId("d4a0aba637d64a9f9a05a575fa757ac2");
        moPubView.loadAd();

    }

}

package com.pranay.fabrictools.mopub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;

import io.fabric.sdk.android.Fabric;

public class FullScreenAdAdActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {
    private MoPubInterstitial interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this);
        setContentView(R.layout.activity_full_screen_activity);


        // TODO: Replace this test id with your personal ad unit id
        interstitial = new MoPubInterstitial(this, "2be8f7ceb2c14c14b57910146144e80e");
        interstitial.setInterstitialAdListener(this);
        interstitial.load();

    }

    @Override
    protected void onDestroy() {
        interstitial.destroy();
        super.onDestroy();
    }

    // InterstitialAdListener methods
    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        // This sample automatically shows the ad as soon as it's loaded, but
        // you can move this show call to a time more appropriate for your app.
        if (interstitial.isReady()) {
            interstitial.show();
        }
    }


    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        Log.d("MoPub", "Interstitial load failed: " + errorCode);
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
    }

}

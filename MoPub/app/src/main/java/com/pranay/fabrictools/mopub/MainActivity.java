package com.pranay.fabrictools.mopub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_banner_add,btn_fullscreen_add,btn_native_add,btn_native_video_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this);
        setContentView(R.layout.activity_main);

        initViews();


    }

    private void initViews() {
        btn_banner_add=(Button)findViewById(R.id.btn_banner_add);
        btn_fullscreen_add=(Button)findViewById(R.id.btn_fullscreen_add);
        btn_native_add=(Button)findViewById(R.id.btn_native_add);
        btn_native_video_add=(Button)findViewById(R.id.btn_native_video_add);
        btn_banner_add.setOnClickListener(this);
        btn_fullscreen_add.setOnClickListener(this);
        btn_native_add.setOnClickListener(this);
        btn_native_video_add.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_banner_add:
                Intent intent=new Intent(MainActivity.this,BannerAdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_fullscreen_add:
                Intent intentFullScreenAd=new Intent(MainActivity.this,FullScreenAdAdActivity.class);
                startActivity(intentFullScreenAd);
                break;
            case R.id.btn_native_add:break;
            case R.id.btn_native_video_add:break;
        }
    }
}

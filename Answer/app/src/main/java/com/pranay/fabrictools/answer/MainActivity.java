package com.pranay.fabrictools.answer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Answers());
        setContentView(R.layout.activity_main);
        // TODO: Use your own attributes to track content views in your app
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Tweet")
                .putContentType("Video")
                .putContentId("1234")
                .putCustomAttribute("Favorites Count", 20)
                .putCustomAttribute("Screen Orientation", "Landscape"));

    }

    // TODO: Move this method and use your own event name to track your key metrics
    public void onKeyMetric(View view) {
        // TODO: Use your own string attributes to track common values over time
        // TODO: Use your own number attributes to track median value over time
        Answers.getInstance().logCustom(new CustomEvent("Video Played")
                .putCustomAttribute("Category", "Comedy")
                .putCustomAttribute("Length", 350));
    }

}

package com.pranay.fabrictools.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "l8S9GyRXhpTMu5SYNO06rF2VR";
    private static final String TWITTER_SECRET = "psas2hs2SG3u1SZS3wZnn7xe4xpeDf3LHe6yicRYz6O7ZaCdRh";

    private TwitterLoginButton loginButton;
    private TextView tvTwitterUserName;
    private Button btnPostTweet,btnShowUserTimeLine;
    private LinearLayout linerLayoutTweetContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        initTwitterLogin();
    }

    private void initViews() {
        tvTwitterUserName = (TextView) findViewById(R.id.tvTwitterUserName);
        btnPostTweet = (Button) findViewById(R.id.btnShowTweet);
        btnPostTweet.setOnClickListener(this);
        btnShowUserTimeLine=(Button)findViewById(R.id.btnShowUserTimeLine);
        btnShowUserTimeLine.setOnClickListener(this);
        linerLayoutTweetContainer=(LinearLayout)findViewById(R.id.linerLayoutTweetContainer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initTwitterLogin() {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                tvTwitterUserName.setText(msg);
                tvTwitterUserName.setVisibility(View.VISIBLE);
                showToast(msg);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
                tvTwitterUserName.setVisibility(View.GONE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // handle cancelled Twitter login (resets TwitterCore.*AuthHandler.AuthState)
        final TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
        if(twitterAuthClient.getRequestCode()==requestCode) {
            twitterAuthClient.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShowTweet:
                getTweetDetails();
                break;
            case R.id.btnShowUserTimeLine:
                Intent intent=new Intent(MainActivity.this,UserTimeLineActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getTweetDetails() {
        // TODO: Use a more specific parent
        // TODO: Base this Tweet ID on some data from elsewhere in your app
        long tweetId = 686957991338221568L;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                TweetView tweetView = new TweetView(MainActivity.this, result.data);
                linerLayoutTweetContainer.addView(tweetView);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Load Tweet failure", exception);
            }
        });

    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}

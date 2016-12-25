package com.pranay.fabrictools.twitter;

import android.app.ListActivity;
import android.os.Bundle;

import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TwitterListTimeline;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class UserTimeLineActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_timeline);
        getTimeLine();
    }

    private void getTimeLine() {
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("pranaypatel_")
                .build();

        //SearchTimeline which shows Tweets matching with your query “#{hashtag}”:
        SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#androiddev")
                .build();

        //CollectionTimeline for the Fabric Picks example collection of Tweets:

        CollectionTimeline collectionTimeline = new CollectionTimeline.Builder()
                .id(569961150045896704L)
                .build();


        //TwitterListTimeline for the @twitterdev national-parks list can be created with the owner screen name (or user id) and list name:

        final TwitterListTimeline twitterListTimeline = new TwitterListTimeline.Builder()
                .slugWithOwnerScreenName("national-parks", "twitterdev")
                .build();


        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                /*.setTimeline(searchTimeline)*/
                /*.setTimeline(collectionTimeline)*/
                /*.setTimeline(twitterListTimeline)*/
                .build();

        setListAdapter(adapter);

    }
}

package com.naxsoft.lunchinhell.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.naxsoft.lunchinhell.data.RestaurantDS;
import com.naxsoft.lunchinhell.data.VoteDS;
import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.domain.Vote;

import java.util.ArrayList;

/**
 * Created by Iouri on 25/04/2015.
 */
public class RESTService extends IntentService {

    public static final String LIST_RESTAURANTS = "LIST-RESTAURANTS";
    public static final String SAVE_VOTE = "SAVE-VOTE";
    public static final String LIST_VOTES = "LIST_VOTES";
    public static final String REFRESH_RESTAURANTS = "REFRESH-RESTAURANTS";
    public static final String REFRESH_VOTES = "REFRESH-VOTES";

    RestaurantDS restaurantDS = new RestaurantDS();
    VoteDS voteDS = new VoteDS();

    public RESTService() {
        super("REST Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (LIST_RESTAURANTS.equals(action)) {
            refreshRestaurants();
        } else if (LIST_VOTES.equals(action)) {
            refreshVotes();
        } else if (SAVE_VOTE.equals(action)) {
            Vote vote = intent.getParcelableExtra("vote");
            voteDS.submitVote(vote);
            refreshRestaurants();
        }
    }

    private void refreshVotes() {
        ArrayList<Restaurant> restaurants = voteDS.getVotes();
        Intent targetIntent = new Intent(REFRESH_VOTES);
        targetIntent.putParcelableArrayListExtra("restaurants", restaurants);
        LocalBroadcastManager.getInstance(this).sendBroadcast(targetIntent);
    }

    private void refreshRestaurants() {
        ArrayList<Restaurant> restaurants = restaurantDS.getRestaurants();
        Intent targetIntent = new Intent(REFRESH_RESTAURANTS);
        targetIntent.putParcelableArrayListExtra("restaurants", restaurants);
        LocalBroadcastManager.getInstance(this).sendBroadcast(targetIntent);
    }
}

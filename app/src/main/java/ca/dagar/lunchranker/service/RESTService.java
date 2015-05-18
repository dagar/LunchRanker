package ca.dagar.lunchranker.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import ca.dagar.lunchranker.data.PlacesDS;
import ca.dagar.lunchranker.data.RestaurantDS;
import ca.dagar.lunchranker.data.VoteDS;
import ca.dagar.lunchranker.domain.Restaurant;
import ca.dagar.lunchranker.domain.Vote;

import java.util.ArrayList;

public class RESTService extends IntentService {

    public static final String LIST_RESTAURANTS = "LIST-RESTAURANTS";
    public static final String SAVE_VOTE = "SAVE-VOTE";
    public static final String LIST_VOTES = "LIST_VOTES";
    public static final String REFRESH_RESTAURANTS = "REFRESH-RESTAURANTS";
    public static final String REFRESH_VOTES = "REFRESH-VOTES";

    RestaurantDS restaurantDS = new RestaurantDS();
    VoteDS voteDS = new VoteDS();
    FallbackLocationTracker locationTracker;

    public RESTService() {
        super("REST Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        locationTracker = new FallbackLocationTracker(this);
        locationTracker.start(new LocationTracker.LocationUpdateListener() {
            @Override
            public void onUpdate(Location oldLoc, long oldTime, Location newLoc, long newTime) {
                Log.d("Location", "Old " + oldLoc + " New " + newLoc);
            }
        });
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
            try {
                voteDS.submitVote(vote);
                refreshRestaurants();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void refreshVotes() {
        ArrayList<Restaurant> restaurants = null;
        try {
            restaurants = voteDS.getVotes();
            Intent targetIntent = new Intent(REFRESH_VOTES);
            targetIntent.putParcelableArrayListExtra("restaurants", restaurants);
            LocalBroadcastManager.getInstance(this).sendBroadcast(targetIntent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void refreshRestaurants() {
        try {
            ArrayList<Restaurant> allResstaurants = restaurantDS.getRestaurants();
            Location location = locationTracker.getLocation();
            if (null != location) {
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                ArrayList<Restaurant> placesRestaurants = new PlacesDS().getRestaurants(longitude, latitude, 100);
                allResstaurants.addAll(placesRestaurants);
            }

            Intent targetIntent = new Intent(REFRESH_RESTAURANTS);
            targetIntent.putParcelableArrayListExtra("restaurants", allResstaurants);
            LocalBroadcastManager.getInstance(this).sendBroadcast(targetIntent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

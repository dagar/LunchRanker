package com.naxsoft.lunchinhell.data;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.naxsoft.lunchinhell.Consts;
import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.domain.Vote;
import com.naxsoft.lunchinhell.service.WebHelper;
import com.naxsoft.lunchinhell.service.WebResult;

import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Iouri on 24/04/2015.
 */
public class VoteDS {

    private ArrayList<Restaurant> votes = new ArrayList<>();

    public boolean submitVote(final Vote vote) {

                WebHelper webHelper = new WebHelper();
                Boolean rc = false;
                try {
                    String input = "restaurantId=" + vote.getRestaurantId() + "&vote=" + vote.getVoteCount();
                    WebResult result = webHelper.executeHTTP(Consts.RESTAURANT_VOTE, "POST", input);
                    rc = HttpURLConnection.HTTP_OK == result.getHttpCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return rc;

    }



    public ArrayList<Restaurant> getVotes() {
                WebHelper webHelper = new WebHelper();
                ArrayList<Restaurant> restaurants = new ArrayList<>();
                try {
                    WebResult result = webHelper.executeHTTP(Consts.RESTANTANT_RESULT, "GET", null);
                    JsonReader reader = new JsonReader(new StringReader(result.getHttpBody()));
                    reader.beginArray();


                    while (reader.hasNext()) {
                        reader.beginObject();
                        String id = "";
                        String restaurantName = "";
                        String vote = "";
                        while (reader.hasNext()) {
                            String name = reader.nextName();
                            if (name.equals("id")) {
                                id = reader.nextString();
                            } else if (name.equals("name")) {
                                restaurantName = reader.nextString();
                            } else if (name.equals("votes")) {
                                vote = reader.nextString();
                            } else {
                                reader.skipValue();
                            }
                        }

                        restaurants.add(new Restaurant(restaurantName, Integer.parseInt(id), Integer.parseInt(vote), false));
                        reader.endObject();
                    }
                    reader.endArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return restaurants;
    }
}

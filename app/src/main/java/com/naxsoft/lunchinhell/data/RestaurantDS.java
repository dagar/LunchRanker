package com.naxsoft.lunchinhell.data;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.naxsoft.lunchinhell.Consts;
import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.domain.UserAccount;
import com.naxsoft.lunchinhell.domain.VoteState;
import com.naxsoft.lunchinhell.service.WebHelper;
import com.naxsoft.lunchinhell.service.WebResult;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Iouri on 24/04/2015.
 */
public class RestaurantDS {

    private static final int randMin = 2;
    private static final int randMax = 12;

    private Random rand = new Random(System.currentTimeMillis());



    public ArrayList<Restaurant> getRestaurants() {
        AsyncTask<Boolean, Void, ArrayList<Restaurant>> asyncTask = new AsyncTask<Boolean, Void, ArrayList<Restaurant>>() {
            @Override
            protected ArrayList<Restaurant> doInBackground(Boolean... params) {
                WebHelper webHelper = new WebHelper();
                ArrayList<Restaurant> restaurants = new ArrayList<>();
                try {
                    WebResult result = webHelper.executeHTTP(Consts.RESTAURANT_LIST, "GET", null);
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
                        VoteState state;
                        if ("1".equals(vote)) {
                            state = new VoteState(true, false);
                        } else {
                            state = new VoteState(false, true);
                        }
                        restaurants.add(new Restaurant(restaurantName, Integer.parseInt(id), 0, state));
                        reader.endObject();
                    }
                    reader.endArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return restaurants;
            }
        };
        ArrayList<Restaurant> rc = new ArrayList<Restaurant>();
        try {
            rc = asyncTask.execute(true).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }
}

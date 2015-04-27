package com.naxsoft.lunchinhell.data;

import android.util.JsonReader;

import com.naxsoft.lunchinhell.Consts;
import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.service.WebHelper;
import com.naxsoft.lunchinhell.service.WebResult;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class RestaurantDS {
    public ArrayList<Restaurant> getRestaurants() {
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

                boolean voteValue = vote.equals("1") ? true : false;
                restaurants.add(new Restaurant(restaurantName, Integer.parseInt(id), 0, voteValue));
                reader.endObject();
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}

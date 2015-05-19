package ca.dagar.lunchranker.data;

import android.os.Looper;
import android.util.JsonReader;

import ca.dagar.lunchranker.Consts;
import ca.dagar.lunchranker.asyncTasks.ParcelableList;
import ca.dagar.lunchranker.domain.Restaurant;
import ca.dagar.lunchranker.service.WebHelper;
import ca.dagar.lunchranker.service.WebResult;

import java.io.StringReader;

public class RestaurantDS {
    public ParcelableList<Restaurant> getRestaurants() throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) { throw new AssertionError("This code should not run on the main thread"); }

        WebHelper webHelper = new WebHelper();
        ParcelableList<Restaurant> restaurants = new ParcelableList<>();

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
        return restaurants;
    }
}

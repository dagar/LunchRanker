package ca.dagar.lunchranker.data;

import android.os.Looper;
import android.util.JsonReader;
import android.util.Log;

import ca.dagar.lunchranker.Consts;
import ca.dagar.lunchranker.asyncTasks.ParcelableList;
import ca.dagar.lunchranker.domain.Restaurant;
import ca.dagar.lunchranker.domain.Vote;
import ca.dagar.lunchranker.service.WebHelper;
import ca.dagar.lunchranker.service.WebResult;

import java.io.StringReader;
import java.net.HttpURLConnection;

/**
 * Created by Iouri on 24/04/2015.
 */
public class VoteDS {
    public boolean submitVote(final Vote vote) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) { throw new AssertionError("This code should not run on the main thread"); }

        String input = "restaurantId=" + vote.getRestaurantId() + "&vote=" + vote.getVoteCount();

        WebHelper webHelper = new WebHelper();
        WebResult result = webHelper.executeHTTP(Consts.RESTAURANT_VOTE, "POST", input);

        boolean rc = HttpURLConnection.HTTP_OK == result.getHttpCode();
        if (!rc) {
            Log.e("AccountDS", result.getHttpBody());
        }
        return rc;
    }

    public ParcelableList<Restaurant> getVotes() throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) { throw new AssertionError("This code should not run on the main thread"); }

        WebHelper webHelper = new WebHelper();
        ParcelableList<Restaurant> restaurants = new ParcelableList<>();

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

        return restaurants;
    }
}

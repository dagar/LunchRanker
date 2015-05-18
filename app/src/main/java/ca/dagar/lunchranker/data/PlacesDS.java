package ca.dagar.lunchranker.data;

import android.os.Looper;

import ca.dagar.lunchranker.asyncTasks.ParcelableList;
import ca.dagar.lunchranker.domain.Restaurant;

import java.util.List;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

/**
 * Created by Iouri on 15/05/2015.
 */
public class PlacesDS {
    private static final String GOOGLE_MAPS_API_KEY = "AIzaSyCXMbO5daZfLTxte1Z4ngJTv6Pe6pGdpkQ";

    public ParcelableList<Restaurant> getRestaurants(double longitude, double latitude, int radius) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) { throw new AssertionError("This code should not run on the main thread"); }

        ParcelableList<Restaurant> restaurants = new ParcelableList<>();

        GooglePlaces client = new GooglePlaces(GOOGLE_MAPS_API_KEY);
        client.setDebugModeEnabled(true);

        List<Place> places = client.getPlacesByQuery("restaurant",
                Param.name("location").value(latitude + "," + longitude),
                Param.name("radius").value(radius));

        for(Place p : places) {
            restaurants.add(new Restaurant(p.getName(), p.getPlaceId().hashCode(), 0, false));
        }

        return restaurants;
    }
}

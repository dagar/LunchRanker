package ca.dagar.lunchranker.asyncTasks;

import android.os.AsyncTask;

import ca.dagar.lunchranker.data.PlacesDS;
import ca.dagar.lunchranker.domain.Restaurant;

/**
 * Created by Iouri on 15/05/2015.
 */
public class GetPlacesTask extends AsyncTask<Object, Integer, AsyncTaskResult<ParcelableList<Restaurant>>> {
    @Override
    public AsyncTaskResult<ParcelableList<Restaurant>> doInBackground(Object... params) {
        PlacesDS ds = new PlacesDS();
        double longitude = ((Double) params[0]).doubleValue();
        double latitude = ((Double) params[1]).doubleValue();
        int radius = ((Integer) params[2]).intValue();

        try {
            return new AsyncTaskResult(ds.getRestaurants(longitude, latitude, radius));
        } catch (Exception e) {
            return new AsyncTaskResult(e);
        }
    }
}
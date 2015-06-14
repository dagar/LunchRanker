package ca.dagar.lunchranker.asyncTasks;

import android.os.AsyncTask;

import ca.dagar.lunchranker.data.RestaurantDS;
import ca.dagar.lunchranker.domain.Restaurant;

/**
 * Created by Iouri on 15/05/2015.
 */
public class GetRestaurantsTask extends AsyncTask<Void, Integer, AsyncTaskResult<ParcelableList<Restaurant>>> {
    @Override
    protected AsyncTaskResult<ParcelableList<Restaurant>> doInBackground(Void... params) {
        RestaurantDS ds = new RestaurantDS();
        try {
            return new AsyncTaskResult(ds.getRestaurants());
        } catch (Exception e) {
            return new AsyncTaskResult(e);
        }
    }
}
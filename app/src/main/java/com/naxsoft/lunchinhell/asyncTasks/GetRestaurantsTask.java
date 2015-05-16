package com.naxsoft.lunchinhell.asyncTasks;

import android.os.AsyncTask;

import com.naxsoft.lunchinhell.data.RestaurantDS;
import com.naxsoft.lunchinhell.domain.Restaurant;

import java.util.ArrayList;
import java.util.List;

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
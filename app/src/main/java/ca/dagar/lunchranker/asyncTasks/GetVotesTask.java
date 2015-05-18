package ca.dagar.lunchranker.asyncTasks;

import android.os.AsyncTask;

import ca.dagar.lunchranker.data.VoteDS;
import ca.dagar.lunchranker.domain.Restaurant;

public class GetVotesTask extends AsyncTask<Void, Integer, AsyncTaskResult<ParcelableList<Restaurant>>> {
    @Override
    protected AsyncTaskResult<ParcelableList<Restaurant>> doInBackground(Void... params) {
        try {
            VoteDS voteDS = new VoteDS();
            return new AsyncTaskResult(voteDS.getVotes());
        } catch (Exception e) {
            return new AsyncTaskResult(e);
        }
    }
}

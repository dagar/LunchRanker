package com.naxsoft.lunchinhell.asyncTasks;

import android.os.AsyncTask;

import com.naxsoft.lunchinhell.data.VoteDS;
import com.naxsoft.lunchinhell.domain.Restaurant;

import java.util.ArrayList;

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

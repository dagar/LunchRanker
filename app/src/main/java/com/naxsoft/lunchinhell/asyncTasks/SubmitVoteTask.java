package com.naxsoft.lunchinhell.asyncTasks;

import android.os.AsyncTask;

import com.naxsoft.lunchinhell.data.VoteDS;
import com.naxsoft.lunchinhell.domain.Vote;

/**
 * Created by Iouri on 15/05/2015.
 */
public class SubmitVoteTask extends AsyncTask<Vote, Integer, AsyncTaskResult<BoolResult>> {
    @Override
    protected AsyncTaskResult<BoolResult> doInBackground(Vote... params) {
        VoteDS voteDS = new VoteDS();
        try {
            boolean voteResult = voteDS.submitVote(params[0]);
            return new AsyncTaskResult<BoolResult>(new BoolResult(voteResult));
        } catch (Exception e) {
            return new AsyncTaskResult(e);
        }
    }
}

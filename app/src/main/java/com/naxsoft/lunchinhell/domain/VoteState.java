package com.naxsoft.lunchinhell.domain;

/**
 * Created by Iouri on 24/04/2015.
 */
public class VoteState {
    private boolean voteUp;
    private boolean voteDown;

    public VoteState(boolean voteUp, boolean voteDown) {
        this.voteUp = voteUp;
        this.voteDown = voteDown;
    }

    public boolean isVoteUp() {
        return voteUp;
    }

    public boolean isVoteDown() {
        return voteDown;
    }
}

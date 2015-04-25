package com.naxsoft.lunchinhell.data;

import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.domain.Vote;
import com.naxsoft.lunchinhell.domain.VoteState;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Iouri on 24/04/2015.
 */
public class VoteDS {

    private static final int randMin = 2;
    private static final int randMax = 12;
    private Random rand = new Random(System.currentTimeMillis());


    public SortedSet<Vote> getVotes() {
        SortedSet<Vote> result = new TreeSet<>();
        for (int i = 0; i < getNextRandInt(); i++) {
            boolean vote = rand.nextBoolean();
            VoteState voteState = new VoteState(vote, !vote);
            result.add(new Vote(new Restaurant("Name " + i, i, voteState), getNextRandInt()));
        }
        return result;
    }

    private int getNextRandInt() {
        return rand.nextInt((randMax - randMin) + 1) + randMin;
    }

    public boolean submitVote(int restaurantId, int vote) {
        return true;
    }
}

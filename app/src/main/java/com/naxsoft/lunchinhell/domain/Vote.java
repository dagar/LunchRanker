package com.naxsoft.lunchinhell.domain;

/**
 * Created by Iouri on 24/04/2015.
 */
public class Vote implements Comparable<Vote> {
    Restaurant restaurant;
    int voteCount;

    public Vote(Restaurant restaurant, int voteCount) {
        this.restaurant = restaurant;
        this.voteCount = voteCount;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public int compareTo(Vote another) {
        if (voteCount < another.voteCount) {
            return 1;
        } else if (voteCount > another.voteCount) {
            return -1;
        } else {
            return  0;
        }
    }
}

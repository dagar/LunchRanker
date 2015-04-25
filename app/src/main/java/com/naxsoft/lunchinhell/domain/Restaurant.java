package com.naxsoft.lunchinhell.domain;

/**
 * Created by Iouri on 24/04/2015.
 */
public class Restaurant {
    private String name;
    private int id;

    VoteState voteType;

    public Restaurant(String name, int id, VoteState voteType) {
        this.name = name;
        this.id = id;
        this.voteType = voteType;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public VoteState getVoteType() {
        return voteType;
    }
}

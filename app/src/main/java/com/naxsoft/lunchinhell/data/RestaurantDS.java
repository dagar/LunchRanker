package com.naxsoft.lunchinhell.data;

import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.domain.VoteState;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Iouri on 24/04/2015.
 */
public class RestaurantDS {

    private static final int randMin = 2;
    private static final int randMax = 12;
    private Random rand = new Random(System.currentTimeMillis());



    public Collection<Restaurant> getRestaurants() {
        Collection<Restaurant> result = new HashSet<>();
        for (int i = 0; i < rand.nextInt((randMax - randMin) + 1) + randMin; i++) {
            boolean vote = rand.nextBoolean();
            result.add(new Restaurant("Name " + i, i, new VoteState(vote, !vote)));
        }
        return result;
    }
}

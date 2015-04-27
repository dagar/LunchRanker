package ca.dagar.lunchranker.data;

import ca.dagar.lunchranker.domain.Restaurant;
import ca.dagar.lunchranker.domain.VoteState;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Iouri on 24/04/2015.
 */
public class RestaurantDS {

    private static final int randMin = 2;
    private static final int randMax = 12;
    private Random rand = new Random(System.currentTimeMillis());



    public ArrayList<Restaurant> getRestaurants() {
        ArrayList<Restaurant> result = new ArrayList<>();
        for (int i = 0; i < rand.nextInt((randMax - randMin) + 1) + randMin; i++) {
            boolean vote = rand.nextBoolean();
            result.add(new Restaurant("Name " + i, i, i, new VoteState(vote, !vote)));
        }
        return result;
    }
}

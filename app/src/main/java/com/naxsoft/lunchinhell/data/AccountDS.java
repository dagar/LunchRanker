package com.naxsoft.lunchinhell.data;

import com.naxsoft.lunchinhell.domain.UserAccount;

import java.util.Random;

/**
 * Created by Iouri on 25/04/2015.
 */
public class AccountDS {
    public boolean register(UserAccount account) {
        return new Random().nextBoolean();
    }

    public boolean login(UserAccount account) {
        return new Random().nextBoolean();
    }
}

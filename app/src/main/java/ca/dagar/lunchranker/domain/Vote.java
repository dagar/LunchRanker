package ca.dagar.lunchranker.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Iouri on 24/04/2015.
 */
public class Vote implements Comparable<Vote>, Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Vote createFromParcel(Parcel in) {
            return new Vote(in);
        }

        public Vote[] newArray(int size) {
            return new Vote[size];
        }
    };
    int restaurantId;
    int voteCount;

    public Vote(int restaurantId, int voteCount) {
        this.restaurantId = restaurantId;
        this.voteCount = voteCount;
    }

    public Vote(Parcel in) {
        this.restaurantId = in.readInt();
        this.voteCount = in.readInt();
    }

    public int getRestaurantId() {
        return restaurantId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.restaurantId);
        dest.writeInt(this.voteCount);
    }
}

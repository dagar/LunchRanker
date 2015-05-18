package ca.dagar.lunchranker.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Iouri on 24/04/2015.
 */
public class Restaurant implements Parcelable, Comparable<Restaurant> {
    public static final Creator CREATOR = new Creator() {
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
    private String name;
    private int id;
    private int voteCount;
    boolean voteValue;

    public Restaurant(String name, int id, int voteCount, boolean voteValue) {
        this.name = name;
        this.id = id;
        this.voteCount = voteCount;
        this.voteValue = voteValue;
    }

    public Restaurant(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.voteCount = in.readInt();
        boolean[] temp = new boolean[1];
        in.readBooleanArray(temp);
        voteValue = temp[0];
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVoteValue() {
        return voteValue;
    }

    @Override
    public int compareTo(Restaurant another) {
        if (voteCount < another.voteCount) {
            return 1;
        } else if (voteCount > another.voteCount) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeInt(this.voteCount);
        dest.writeBooleanArray(new boolean[]{voteValue});
    }
}

package com.naxsoft.lunchinhell.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Iouri on 24/04/2015.
 */
public class Restaurant implements Parcelable, Comparable<Restaurant> {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
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
    VoteState voteType;

    public Restaurant(String name, int id, int voteCount, VoteState voteType) {
        this.name = name;
        this.id = id;
        this.voteCount = voteCount;
        this.voteType = voteType;
    }

    public Restaurant(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.voteCount = in.readInt();
        this.voteType = in.readParcelable(VoteState.class.getClassLoader());
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

    public VoteState getVoteType() {
        return voteType;
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
        dest.writeParcelable(this.voteType, flags);
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
}

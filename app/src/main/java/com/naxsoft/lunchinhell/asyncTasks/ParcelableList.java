package com.naxsoft.lunchinhell.asyncTasks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Iouri on 15/05/2015.
 */
public class ParcelableList<T> extends ArrayList<T> implements Parcelable {

    public static final Creator CREATOR = new Creator() {
        public ParcelableList createFromParcel(Parcel in) {
            return new ParcelableList(in);
        }

        public ParcelableList[] newArray(int size) {
            return new ParcelableList[size];
        }
    };

    public ParcelableList(Parcel in) {
        addAll(in.readArrayList(null));
    }

    public ParcelableList() {
        super();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this);
    }
}

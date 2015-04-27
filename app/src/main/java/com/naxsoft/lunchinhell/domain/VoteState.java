//package com.naxsoft.lunchinhell.domain;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
///**
// * Created by Iouri on 24/04/2015.
// */
//public class VoteState implements Parcelable {
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public VoteState createFromParcel(Parcel in) {
//            return new VoteState(in);
//        }
//
//        public VoteState[] newArray(int size) {
//            return new VoteState[size];
//        }
//    };
//    private boolean voteUp;
//    private boolean voteDown;
//
//    public VoteState(boolean voteUp, boolean voteDown) {
//        this.voteUp = voteUp;
//        this.voteDown = voteDown;
//    }
//
//    public VoteState(Parcel in) {
//        boolean[] temp = new boolean[2];
//        in.readBooleanArray(temp);
//        voteUp = temp[0];
//        voteDown = temp[1];
//    }
//
//
//    public boolean isVoteUp() {
//        return voteUp;
//    }
//
//    public boolean isVoteDown() {
//        return voteDown;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeBooleanArray(new boolean[]{voteUp, voteDown});
//    }
//}

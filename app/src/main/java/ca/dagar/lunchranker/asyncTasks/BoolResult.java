package ca.dagar.lunchranker.asyncTasks;

import android.os.Parcel;
import android.os.Parcelable;

public class BoolResult extends Exception implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public BoolResult createFromParcel(Parcel in) {
            return new BoolResult(in);
        }

        public BoolResult[] newArray(int size) {
            return new BoolResult[size];
        }
    };
    private boolean isSuccess;



    public BoolResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public BoolResult(Parcel in) {
        boolean[] temp = new boolean[1];
        in.readBooleanArray(temp);
        isSuccess = temp[0];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBooleanArray(new boolean[]{isSuccess});
    }
}

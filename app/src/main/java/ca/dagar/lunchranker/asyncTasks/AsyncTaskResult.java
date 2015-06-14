package ca.dagar.lunchranker.asyncTasks;

import android.os.Parcel;
import android.os.Parcelable;

public class AsyncTaskResult<T extends Parcelable> implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AsyncTaskResult createFromParcel(Parcel in) {
            return new AsyncTaskResult(in);
        }

        public AsyncTaskResult[] newArray(int size) {
            return new AsyncTaskResult[size];
        }
    };
    private T result;
    private Exception error;

    public AsyncTaskResult(Parcel in) {
        this.result = in.readParcelable(null);
        try {
            in.readException();
        } catch (RuntimeException e) {
            error = e;
        }

    }

    public T getResult() {
        return result;
    }
    public Exception getError() {
        return error;
    }

    public AsyncTaskResult(T result) {
        super();
        this.result = result;
    }


    public AsyncTaskResult(Exception error) {
        super();
        this.error = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.result, flags);
        dest.writeException(error);
    }
}
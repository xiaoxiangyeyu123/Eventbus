package eventbus.yeyu.com.library.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：潇湘夜雨 on 2018/8/25.
 * 邮箱：879689064@qq.com
 */
public class Request implements Parcelable {
    private String data;

    protected Request(Parcel in) {
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}

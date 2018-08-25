package eventbus.yeyu.com.library.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：潇湘夜雨 on 2018/8/25.
 * 邮箱：879689064@qq.com
 */
public class Responce implements Parcelable{
    protected Responce(Parcel in) {
    }

    public static final Creator<Responce> CREATOR = new Creator<Responce>() {
        @Override
        public Responce createFromParcel(Parcel in) {
            return new Responce(in);
        }

        @Override
        public Responce[] newArray(int size) {
            return new Responce[size];
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

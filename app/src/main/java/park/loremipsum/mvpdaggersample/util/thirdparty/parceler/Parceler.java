package park.loremipsum.mvpdaggersample.util.thirdparty.parceler;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunwoopark on 2015. 10. 31..
 */
public interface Parceler {
    <T> ArrayList<Parcelable> parcel(List<T> list);

    <T> List<T> unparcel(Class<T> clazz, List<Parcelable> list);
}

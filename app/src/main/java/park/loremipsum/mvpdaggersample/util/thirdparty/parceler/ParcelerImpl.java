package park.loremipsum.mvpdaggersample.util.thirdparty.parceler;

import android.os.Parcelable;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunwoopark on 2015. 10. 31..
 */
public class ParcelerImpl implements Parceler {
    public <T> ArrayList<Parcelable> parcel(List<T> list) {
        final ArrayList<Parcelable> parceledList = new ArrayList<>();
        for (T type : list) {
            final Parcelable parcelable = Parcels.wrap(type);
            parceledList.add(parcelable);
        }
        return parceledList;
    }

    public <T> List<T> unparcel(Class<T> clazz, List<Parcelable> list) {
        final List<T> unparceledList = new ArrayList<>();
        for (Parcelable parcelable : list) {
            final T unparceledT = Parcels.unwrap(parcelable);
            unparceledList.add(unparceledT);
        }
        return unparceledList;
    }
}

package park.loremipsum.mvpdaggersample.util.thirdparty.glide;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

public class GlideWrapperImpl implements GlideWrapper {

    private final WeakReference<Activity> weakActivity;

    public GlideWrapperImpl(Activity activity) {
        this.weakActivity = new WeakReference<>(activity);
    }

    public void load(String url, ImageView imageView) {
        final Activity activity = weakActivity.get();
        if (activity != null && !activity.isDestroyed()) {
            Glide.with(activity).load(url).into(imageView);
        }
    }
}

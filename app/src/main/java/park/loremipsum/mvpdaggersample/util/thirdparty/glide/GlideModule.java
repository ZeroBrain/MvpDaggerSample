package park.loremipsum.mvpdaggersample.util.thirdparty.glide;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.PerActivity;

@Module
public class GlideModule {
    @Provides
    @PerActivity
    GlideWrapper provideGlideWrapper(Activity activity) {
        return new GlideWrapperImpl(activity);
    }
}

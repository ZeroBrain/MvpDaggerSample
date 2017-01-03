package park.loremipsum.mvpdaggersample.util.thirdparty.glide;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.ActivityScope;

@Module
public class GlideModule {
    @Provides
    @ActivityScope
    GlideWrapper provideGlideWrapper(Activity activity) {
        return new GlideWrapperImpl(activity);
    }
}

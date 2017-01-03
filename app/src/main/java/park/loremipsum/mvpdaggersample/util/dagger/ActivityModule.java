package park.loremipsum.mvpdaggersample.util.dagger;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.ActivityScope;
import park.loremipsum.mvpdaggersample.util.thirdparty.glide.GlideModule;

@Module(includes = {GlideModule.class, FragmentComponentBinder.class})
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }
}

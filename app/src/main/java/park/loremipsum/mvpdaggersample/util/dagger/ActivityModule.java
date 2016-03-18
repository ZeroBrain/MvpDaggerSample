package park.loremipsum.mvpdaggersample.util.dagger;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.PerActivity;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @PerActivity
    LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }
}

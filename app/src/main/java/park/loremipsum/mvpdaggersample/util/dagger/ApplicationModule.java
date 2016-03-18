package park.loremipsum.mvpdaggersample.util.dagger;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.InjectionApplication;

@Module
public class ApplicationModule {
    private final InjectionApplication application;

    public ApplicationModule(InjectionApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }
}

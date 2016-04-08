package park.loremipsum.mvpdaggersample.dagger.module;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.dagger.injector.MockHolder;

/**
 * Created by hyunwoopark on 2016. 4. 8..
 */
@Module
public class TestApplicationModule {

    private final InjectionApplication application;

    public TestApplicationModule(InjectionApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return MockHolder.returnMockIfExist(application, Context.class);
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return MockHolder.returnMockIfExist(application.getResources(), Resources.class);
    }
}

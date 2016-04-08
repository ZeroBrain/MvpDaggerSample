package park.loremipsum.mvpdaggersample.dagger.module;

import android.content.Context;
import android.content.res.Resources;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.dagger.injector.TestInjectorCreator;

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
        for (Object object : TestInjectorCreator.mockList) {
            if (object instanceof Context) {
                return (Context) object;
            }
        }
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        for (Object object : TestInjectorCreator.mockList) {
            if (object instanceof Resources) {
                return (Resources) object;
            }
        }
        return application.getResources();
    }
}

package park.loremipsum.mvpdaggersample;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.util.dagger.injector.ApplicationInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.InjectorCreator;

public class InjectionApplication extends Application {

    @VisibleForTesting
    @Getter
    protected InjectorCreator injectorCreator;

    @Override
    public void onCreate() {
        super.onCreate();
        injectorCreator = makeInjectorCreator();
        inject();
    }

    @VisibleForTesting
    protected InjectorCreator makeInjectorCreator() {
        return new InjectorCreator();
    }

    @VisibleForTesting
    protected final void inject() {
        final ApplicationInjector applicationInjector = injectorCreator.makeApplicationInjector(this);
        applicationInjector.inject(this);
    }
}

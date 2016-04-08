package park.loremipsum.mvpdaggersample;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.util.dagger.injector.ApplicationInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.InjectorCreator;

public class InjectionApplication extends Application {

    @Getter
    private InjectorCreator injectorCreator;

    @Override
    public void onCreate() {
        super.onCreate();
        makeInjectorCreator();
        inject();
    }

    private void makeInjectorCreator() {
        injectorCreator = new InjectorCreator();
    }

    @VisibleForTesting
    public void changeInjector(InjectorCreator injectorCreator) {
        this.injectorCreator = injectorCreator;
        inject();
    }

    private void inject() {
        final ApplicationInjector applicationInjector = injectorCreator.makeApplicationInjector(this);
        applicationInjector.inject(this);
    }
}

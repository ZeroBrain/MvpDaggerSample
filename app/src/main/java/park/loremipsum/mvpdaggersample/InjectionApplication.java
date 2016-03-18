package park.loremipsum.mvpdaggersample;

import android.app.Application;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.util.dagger.injector.ApplicationInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.InjectorCreator;

public class InjectionApplication extends Application {

    @Getter
    private InjectorCreator injectorCreator;

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    private void inject() {
        injectorCreator = new InjectorCreator();
        final ApplicationInjector applicationInjector = injectorCreator.makeApplicationInjector(this);
        applicationInjector.inject(this);
    }
}

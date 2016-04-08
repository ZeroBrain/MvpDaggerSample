package park.loremipsum.mvpdaggersample;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import park.loremipsum.mvpdaggersample.util.dagger.component.ApplicationComponent;
import park.loremipsum.mvpdaggersample.util.dagger.injector.ApplicationInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.InjectorCreator;

public class InjectionApplication extends Application {

    //region For Legacy Code
    @Getter
    private static Context context;

    private static void setContext(InjectionApplication application) {
        context = application;
    }

    public static ApplicationComponent getComponent() {
        return ((InjectionApplication)context).getApplicationComponent();
    }

    @Getter
    ApplicationComponent applicationComponent;
    //endregion

    @VisibleForTesting
    @Getter
    protected InjectorCreator injectorCreator;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
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
        applicationComponent = applicationInjector.getApplicationComponent();
        applicationInjector.inject(this);
    }
}

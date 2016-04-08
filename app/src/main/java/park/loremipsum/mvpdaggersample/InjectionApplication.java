package park.loremipsum.mvpdaggersample;

import android.app.Application;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.util.dagger.ApplicationModule;
import park.loremipsum.mvpdaggersample.util.dagger.component.ApplicationComponent;
import park.loremipsum.mvpdaggersample.util.dagger.component.DaggerApplicationComponent;

public class InjectionApplication extends Application {

    @Getter
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }
}

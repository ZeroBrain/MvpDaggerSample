package park.loremipsum.mvpdaggersample.util.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.util.dagger.injector.ActivityInjector;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;

public class InjectionActivity extends AppCompatActivity {

    @Getter
    private ActivityInjector activityInjector;

    @Inject
    protected EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
    }

    private void inject() {
        final InjectionApplication application = ((InjectionApplication) getApplication());
        activityInjector = application.getInjectorCreator().makeActivityInjector();
        activityInjector.inject(this);
    }
}

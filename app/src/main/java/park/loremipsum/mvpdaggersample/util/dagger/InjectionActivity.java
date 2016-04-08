package park.loremipsum.mvpdaggersample.util.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.util.dagger.component.ActivityComponent;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;

public abstract class InjectionActivity extends AppCompatActivity {

    @Getter
    private ActivityComponent activityComponent;

    @Inject
    protected EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final InjectionApplication application = ((InjectionApplication) getApplication());
        activityComponent = application.getApplicationComponent().plusActivityComponent(new ActivityModule(this));
        inject(activityComponent);
        super.onCreate(savedInstanceState);
    }

    protected abstract void inject(ActivityComponent component);
}

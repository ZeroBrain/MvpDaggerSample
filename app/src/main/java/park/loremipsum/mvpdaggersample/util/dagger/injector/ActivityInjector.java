package park.loremipsum.mvpdaggersample.util.dagger.injector;

import lombok.AccessLevel;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.ui.MainActivity;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.component.ActivityComponent;

public class ActivityInjector {

    @Getter(value = AccessLevel.PACKAGE)
    private final ActivityComponent activityComponent;

    public ActivityInjector(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    public void inject(InjectionActivity activity) {
        if (activity instanceof MainActivity) {
            activityComponent.inject((MainActivity) activity);
        }
    }
}

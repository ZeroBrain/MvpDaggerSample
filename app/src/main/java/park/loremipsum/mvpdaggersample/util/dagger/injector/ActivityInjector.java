package park.loremipsum.mvpdaggersample.util.dagger.injector;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.ui.MainActivity;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.component.ActivityComponent;

public class ActivityInjector {

    @Getter
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

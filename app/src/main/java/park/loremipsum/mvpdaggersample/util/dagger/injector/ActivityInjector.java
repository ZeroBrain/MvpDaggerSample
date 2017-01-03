package park.loremipsum.mvpdaggersample.util.dagger.injector;

import java.lang.reflect.Method;

import javax.inject.Provider;

import android.app.Activity;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.ui.MainActivity;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityComponentBuilder;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityModule;
import park.loremipsum.mvpdaggersample.util.dagger.ApplicationComponent;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityComponent;

public class ActivityInjector {

    private final ApplicationComponent component;

    @Getter
    protected ActivityComponent activityComponent;

    public ActivityInjector(ApplicationComponent component) {
        this.component = component;
    }

    public void inject(InjectionActivity activity) {
        final Provider<ActivityComponentBuilder> builderProvider =
            component.activityComponentBuilderMap().get(activity.getClass());
        try {
            activityComponent =
                (ActivityComponent)builderProvider.get()
                    .activityModule(new ActivityModule(activity))
                    .build();
        } catch (NullPointerException e) {
            throw new RuntimeException("There is no matched Builder of Activity's component."
                + "You must declare Builder in ActivityComponentBinder", e);
        }

        try {
            final Method method = activityComponent.getClass().getMethod("inject", activity.getClass());
            method.invoke(activityComponent, activity);
        } catch (Exception e) {
            throw new RuntimeException("There is no 'inject' method. Subcomponent must have 'inject' method.", e);
        }
    }

    public void makeActivityComponent(Activity activity) {
        final Provider<ActivityComponentBuilder> builderProvider =
            component.activityComponentBuilderMap().get(Activity.class);
        activityComponent = (ActivityComponent)builderProvider.get()
            .activityModule(new ActivityModule(activity))
            .build();
    }
}

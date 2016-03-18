package park.loremipsum.mvpdaggersample.util.dagger.injector;

import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityModule;
import park.loremipsum.mvpdaggersample.util.dagger.ApplicationModule;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;
import park.loremipsum.mvpdaggersample.util.dagger.component.ActivityComponent;
import park.loremipsum.mvpdaggersample.util.dagger.component.ApplicationComponent;
import park.loremipsum.mvpdaggersample.util.dagger.component.DaggerApplicationComponent;
import park.loremipsum.mvpdaggersample.util.dagger.component.FragmentComponent;

public class InjectorCreator {
    private ApplicationComponent applicationComponent;

    public ApplicationInjector makeApplicationInjector(InjectionApplication application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
        return new ApplicationInjector(applicationComponent);
    }

    public ActivityInjector makeActivityInjector(InjectionActivity activity) {
        final ActivityComponent activityComponent = applicationComponent.plusActivityComponent(new ActivityModule(activity));
        return new ActivityInjector(activityComponent);
    }

    public FragmentInjector makeFragmentInjector(InjectionFragment fragment) {
        final ActivityInjector activityInjector = ((InjectionActivity) fragment.getActivity()).getActivityInjector();
        final FragmentComponent fragmentComponent = activityInjector.getActivityComponent().plusFragmentComponent(new FragmentModule(fragment));
        return new FragmentInjector(fragmentComponent);
    }
}

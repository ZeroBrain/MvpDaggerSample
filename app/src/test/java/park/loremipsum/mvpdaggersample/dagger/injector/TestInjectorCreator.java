package park.loremipsum.mvpdaggersample.dagger.injector;

import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.dagger.component.DaggerTestApplicationComponent;
import park.loremipsum.mvpdaggersample.dagger.component.TestActivityComponent;
import park.loremipsum.mvpdaggersample.dagger.component.TestApplicationComponent;
import park.loremipsum.mvpdaggersample.dagger.component.TestFragmentComponent;
import park.loremipsum.mvpdaggersample.dagger.module.TestApplicationModule;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityModule;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;
import park.loremipsum.mvpdaggersample.util.dagger.injector.ActivityInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.FragmentInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.InjectorCreator;

public class TestInjectorCreator extends InjectorCreator {
    private TestApplicationComponent applicationComponent;

    public TestInjectorCreator() {
        MockHolder.init(new MockHolder());
    }

    public TestApplicationInjector makeApplicationInjector(InjectionApplication application) {
        applicationComponent = DaggerTestApplicationComponent.builder()
                .testApplicationModule(new TestApplicationModule(application))
                .build();
        return new TestApplicationInjector(applicationComponent);
    }

    public ActivityInjector makeActivityInjector(InjectionActivity activity) {
        final TestActivityComponent activityComponent = applicationComponent.plusTestActivityComponent(new ActivityModule(activity));
        return new TestActivityInjector(activityComponent);
    }

    public FragmentInjector makeFragmentInjector(InjectionFragment fragment) {
        final TestActivityInjector activityInjector = (TestActivityInjector) ((InjectionActivity) fragment.getActivity()).getActivityInjector();
        final TestActivityComponent activityComponent = activityInjector.getTestActivityComponent();
        final TestFragmentComponent fragmentComponent = activityComponent.plusTestFragmentComponent(new FragmentModule(fragment));
        return new TestFragmentInjector(fragmentComponent);
    }

}

package park.loremipsum.mvpdaggersample.dagger.injector;

import java.util.ArrayList;
import java.util.List;

import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.dagger.component.DaggerTestApplicationComponent;
import park.loremipsum.mvpdaggersample.dagger.component.TestActivityComponent;
import park.loremipsum.mvpdaggersample.dagger.component.TestApplicationComponent;
import park.loremipsum.mvpdaggersample.dagger.component.TestFragmentComponent;
import park.loremipsum.mvpdaggersample.dagger.module.TestApplicationModule;
import park.loremipsum.mvpdaggersample.dagger.module.TestParserModule;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityModule;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;
import park.loremipsum.mvpdaggersample.util.dagger.injector.ActivityInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.FragmentInjector;
import park.loremipsum.mvpdaggersample.util.dagger.injector.InjectorCreator;

public class TestInjectorCreator extends InjectorCreator {
    private TestApplicationComponent applicationComponent;

    private final List<Object> mockList = new ArrayList<>();

    public void addMock(List<Object> mockList) {
        this.mockList.addAll(mockList);
    }

    public TestApplicationInjector makeApplicationInjector(InjectionApplication application) {
        applicationComponent = DaggerTestApplicationComponent.builder()
                .testApplicationModule(new TestApplicationModule(application, mockList))
                .testParserModule(new TestParserModule(mockList))
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

package park.loremipsum.mvpdaggersample.util.dagger.injector;

import android.app.Activity;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityComponent;
import park.loremipsum.mvpdaggersample.util.dagger.ApplicationComponent;
import park.loremipsum.mvpdaggersample.util.dagger.ApplicationModule;
import park.loremipsum.mvpdaggersample.util.dagger.DaggerApplicationComponent;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;

public class InjectorCreator {
	private ApplicationComponent applicationComponent;

	public ApplicationInjector makeApplicationInjector(InjectionApplication application) {
		applicationComponent = DaggerApplicationComponent.builder()
			.applicationModule(new ApplicationModule(application))
			.build();
		return new ApplicationInjector(applicationComponent);
	}

	public ActivityInjector makeActivityInjector() {
		return new ActivityInjector(applicationComponent);
	}

	public FragmentInjector makeFragmentInjector(InjectionFragment fragment) {
		final Activity activity = fragment.getActivity();
		final ActivityInjector activityInjector = getActivityInjector(activity);
		final ActivityComponent activityComponent = activityInjector.getActivityComponent();
		return new FragmentInjector(activityComponent);
	}

	private ActivityInjector getActivityInjector(Activity activity) {
		if (activity instanceof InjectionActivity) {
			final InjectionActivity injectionActivity = (InjectionActivity)activity;
			return injectionActivity.getActivityInjector();
		} else {
			final ActivityInjector activityInjector = makeActivityInjector();
			activityInjector.makeActivityComponent(activity);
			return activityInjector;
		}
	}
}

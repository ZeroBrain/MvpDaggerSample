package park.loremipsum.mvpdaggersample.util.dagger.injector;

import java.lang.reflect.Method;

import javax.inject.Provider;

import park.loremipsum.mvpdaggersample.util.dagger.ActivityComponent;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentComponentBuilder;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;

public class FragmentInjector {

	private final ActivityComponent activityComponent;

	public FragmentInjector(ActivityComponent activityComponent) {
		this.activityComponent = activityComponent;
	}

	public void inject(InjectionFragment fragment) {
		final Provider<FragmentComponentBuilder> builderProvider =
			activityComponent.fragmentComponentBuilderMap().get(fragment.getClass());
		final Object subcomponent;
		try {
			subcomponent = builderProvider.get().fragmentModule(new FragmentModule(fragment)).build();
		} catch (NullPointerException e) {
			throw new RuntimeException("There is no matched Builder of fragment's component."
				+ "You must declare Builder in FragmentComponentBinder", e);
		}

		try {
			final Method method = subcomponent.getClass().getMethod("inject", fragment.getClass());
			method.invoke(subcomponent, fragment);
		} catch (Exception e) {
			throw new RuntimeException("There is no 'inject' method. Subcomponent must have 'inject' method.",
				e);
		}
	}
}

package park.loremipsum.mvpdaggersample.ui;

import dagger.Subcomponent;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityComponent;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityComponentBuilder;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityModule;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.ActivityScope;

/**
 * Created by hyunwoopark on 2017. 1. 3..
 */

@ActivityScope
@Subcomponent(modules = {ActivityModule.class, CardActivityComponent.Module.class})
public interface CardActivityComponent extends ActivityComponent {
	@Subcomponent.Builder
	interface Builder extends ActivityComponentBuilder<CardActivityComponent> {
	}

	@dagger.Module
	class Module {
		// TODO Add dependency For MainActivity
	}

	void inject(CardActivity activity);
}

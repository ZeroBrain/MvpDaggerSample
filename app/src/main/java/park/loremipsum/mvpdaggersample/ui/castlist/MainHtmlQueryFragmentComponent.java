package park.loremipsum.mvpdaggersample.ui.castlist;

import dagger.Subcomponent;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentComponentBuilder;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.FragmentScope;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface MainHtmlQueryFragmentComponent {

	@Subcomponent.Builder
	interface Builder extends FragmentComponentBuilder<MainHtmlQueryFragmentComponent> {
	}

	void inject(MainHtmlQueryFragment fragment);
}

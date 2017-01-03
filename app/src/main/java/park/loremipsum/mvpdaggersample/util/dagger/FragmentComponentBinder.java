package park.loremipsum.mvpdaggersample.util.dagger;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import park.loremipsum.mvpdaggersample.ui.castlist.CardListFragment;
import park.loremipsum.mvpdaggersample.ui.castlist.CardListFragmentComponent;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragment;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragmentComponent;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.FragmentBindKey;

/**
 * Created by hyunwoopark on 2017. 1. 3..
 */

@Module(subcomponents = {MainHtmlQueryFragmentComponent.class,
	CardListFragmentComponent.class})
public abstract class FragmentComponentBinder {
	@Binds
	@IntoMap
	@FragmentBindKey(MainHtmlQueryFragment.class)
	public abstract FragmentComponentBuilder mainHtmlQueryFragment(MainHtmlQueryFragmentComponent.Builder builder);

	@Binds
	@IntoMap
	@FragmentBindKey(CardListFragment.class)
	public abstract FragmentComponentBuilder cardListFragment(CardListFragmentComponent.Builder builder);
}

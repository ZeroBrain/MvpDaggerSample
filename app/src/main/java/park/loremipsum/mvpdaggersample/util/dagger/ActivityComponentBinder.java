package park.loremipsum.mvpdaggersample.util.dagger;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import park.loremipsum.mvpdaggersample.ui.CardActivity;
import park.loremipsum.mvpdaggersample.ui.CardActivityComponent;
import park.loremipsum.mvpdaggersample.ui.MainActivity;
import park.loremipsum.mvpdaggersample.ui.MainActivityComponent;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.ActivityBindKey;

/**
 * Created by hyunwoopark on 2017. 1. 3..
 */
@Module(subcomponents = {MainActivityComponent.class, CardActivityComponent.class})
public abstract class ActivityComponentBinder {
	@Binds
	@IntoMap
	@ActivityBindKey(MainActivity.class)
	public abstract ActivityComponentBuilder mainActivity(MainActivityComponent.Builder builder);

	@Binds
	@IntoMap
	@ActivityBindKey(CardActivity.class)
	public abstract ActivityComponentBuilder cardActivity(CardActivityComponent.Builder builder);
}

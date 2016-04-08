package park.loremipsum.mvpdaggersample.dagger.component;

import dagger.Subcomponent;
import park.loremipsum.mvpdaggersample.ui.MainActivity;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityModule;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.util.dagger.component.ActivityComponent;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.PerActivity;
import park.loremipsum.mvpdaggersample.util.thirdparty.glide.GlideModule;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, GlideModule.class})
public interface TestActivityComponent extends ActivityComponent {

    /**
     * Subcomponents
     */
    TestFragmentComponent plusTestFragmentComponent(FragmentModule module);

    /**
     * Members-injection methods
     */
    void inject(MainActivity activity);
}

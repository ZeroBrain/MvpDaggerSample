package park.loremipsum.mvpdaggersample.util.dagger.injector;

import lombok.AccessLevel;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.ui.castlist.CardListFragment;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragment;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;
import park.loremipsum.mvpdaggersample.util.dagger.component.FragmentComponent;

public class FragmentInjector {

    @Getter(value = AccessLevel.PACKAGE)
    private final FragmentComponent fragmentComponent;

    public FragmentInjector(FragmentComponent fragmentComponent) {
        this.fragmentComponent = fragmentComponent;
    }

    public void inject(InjectionFragment fragment) {
        if (fragment instanceof MainHtmlQueryFragment) {
            fragmentComponent.inject((MainHtmlQueryFragment) fragment);
        } else if (fragment instanceof CardListFragment) {
            fragmentComponent.plusCardList().inject((CardListFragment) fragment);
        }
    }
}

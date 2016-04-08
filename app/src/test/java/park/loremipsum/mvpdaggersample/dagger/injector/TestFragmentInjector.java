package park.loremipsum.mvpdaggersample.dagger.injector;

import lombok.AccessLevel;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.ui.castlist.CardListFragment;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragment;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;
import park.loremipsum.mvpdaggersample.util.dagger.component.FragmentComponent;
import park.loremipsum.mvpdaggersample.util.dagger.injector.FragmentInjector;

public class TestFragmentInjector extends FragmentInjector {

    @Getter(value = AccessLevel.PACKAGE)
    private final FragmentComponent fragmentComponent;

    public TestFragmentInjector(FragmentComponent fragmentComponent) {
        super(fragmentComponent);
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

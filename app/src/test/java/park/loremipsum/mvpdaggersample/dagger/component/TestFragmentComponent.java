package park.loremipsum.mvpdaggersample.dagger.component;

import dagger.Subcomponent;
import park.loremipsum.mvpdaggersample.ui.castlist.CardListFragmentComponent;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragment;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragmentComponent;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.FragmentScope;

@FragmentScope
@Subcomponent(modules = {FragmentModule.class})
public interface TestFragmentComponent extends MainHtmlQueryFragmentComponent {

    CardListFragmentComponent plusCardList();

    /**
     * Members-injection methods
     */
    void inject(MainHtmlQueryFragment fragment);
}

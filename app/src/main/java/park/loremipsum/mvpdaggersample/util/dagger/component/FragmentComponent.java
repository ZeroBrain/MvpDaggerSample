package park.loremipsum.mvpdaggersample.util.dagger.component;

import dagger.Subcomponent;
import park.loremipsum.mvpdaggersample.ui.castlist.CardListComponent;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragment;
import park.loremipsum.mvpdaggersample.util.dagger.FragmentModule;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.PerFragment;

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    CardListComponent plusCardList();

    /**
     * Members-injection methods
     */
    void inject(MainHtmlQueryFragment fragment);
}

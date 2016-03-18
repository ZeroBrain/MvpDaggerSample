package park.loremipsum.mvpdaggersample.ui.castlist;

import dagger.Subcomponent;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.PerFragment;

@PerFragment
@Subcomponent(modules = CardListModule.class)
public interface CardListComponent {
    void inject(CardListFragment fragment);
}

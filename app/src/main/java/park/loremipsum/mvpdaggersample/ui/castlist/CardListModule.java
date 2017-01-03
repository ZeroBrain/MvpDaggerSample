package park.loremipsum.mvpdaggersample.ui.castlist;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.FragmentScope;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;
import park.loremipsum.mvpdaggersample.util.thirdparty.glide.GlideWrapper;
import park.loremipsum.mvpdaggersample.util.thirdparty.parceler.Parceler;

@Module
public class CardListModule {
    @Provides
    @FragmentScope
    CardListPresenter.ViewInterface provideViewInterface(Fragment fragment) {
        return (CardListPresenter.ViewInterface) fragment;
    }

    @Provides
    @FragmentScope
    CastCardAdapter provideCastCardAdapter(Parceler parceler,
                                           GlideWrapper glideWrapper,
                                           LayoutInflater layoutInflater,
                                           EventBus bus) {
        return new CastCardAdapter(parceler, glideWrapper, layoutInflater, bus);
    }

    @Provides
    @FragmentScope
    RecyclerView.LayoutManager providesLinearLayoutManager(Activity activity) {
        return new LinearLayoutManager(activity);
    }
}

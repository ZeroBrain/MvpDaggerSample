package park.loremipsum.mvpdaggersample.util.dagger;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.PerFragment;

@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    @PerFragment
    FragmentManager provideFragmentManager() {
        return fragment.getActivity().getSupportFragmentManager();
    }
}

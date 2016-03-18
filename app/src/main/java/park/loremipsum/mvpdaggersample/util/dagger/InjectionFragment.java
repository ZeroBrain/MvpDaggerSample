package park.loremipsum.mvpdaggersample.util.dagger;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import lombok.AccessLevel;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.util.dagger.injector.FragmentInjector;

public class InjectionFragment extends Fragment {

    @Getter(value = AccessLevel.PACKAGE)
    private FragmentInjector fragmentInjector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
    }

    private void inject() {
        final InjectionApplication application = ((InjectionApplication) getActivity().getApplication());
        fragmentInjector = application.getInjectorCreator().makeFragmentInjector(this);
        fragmentInjector.inject(this);
    }
}

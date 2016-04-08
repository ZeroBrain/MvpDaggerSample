package park.loremipsum.mvpdaggersample.util.dagger;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import park.loremipsum.mvpdaggersample.util.dagger.component.ActivityComponent;
import park.loremipsum.mvpdaggersample.util.dagger.component.FragmentComponent;

public abstract class InjectionFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final ActivityComponent activityComponent = ((InjectionActivity) getActivity()).getActivityComponent();
        final FragmentComponent fragmentComponent = activityComponent.plusFragmentComponent(new FragmentModule(this));
        inject(fragmentComponent);
        super.onCreate(savedInstanceState);
    }

    protected abstract void inject(FragmentComponent fragmentComponent);
}

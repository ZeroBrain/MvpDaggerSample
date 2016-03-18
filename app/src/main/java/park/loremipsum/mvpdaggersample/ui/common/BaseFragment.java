package park.loremipsum.mvpdaggersample.ui.common;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;

public abstract class BaseFragment extends InjectionFragment {
    @Inject
    protected EventBus eventBus;

    public abstract BasePresenter getPresenter();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(getPresenter());
    }

    @Override
    public void onStop() {
        eventBus.unregister(getPresenter());
        super.onStop();
    }
}

package park.loremipsum.mvpdaggersample.ui.castlist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;
import park.loremipsum.mvpdaggersample.util.dagger.component.FragmentComponent;

public class MainHtmlQueryFragment extends InjectionFragment {
    public static final String TAG = MainHtmlQueryFragment.class.getSimpleName();

    @Inject
    MainPageParser pageParser;

    public static MainHtmlQueryFragment instance() {
        return new MainHtmlQueryFragment();
    }

    public MainHtmlQueryFragment() {
        // Default Constructor
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            parseMainPage();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("IsRecreated", true);
    }

    public void parseMainPage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                pageParser.parseMainUrl();
            }
        }).start();
    }

    public void parseTab(final String tabUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                pageParser.parseTab(tabUrl);
            }
        }).start();
    }
}

package park.loremipsum.mvpdaggersample.ui.castlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionFragment;

public class MainHtmlQueryFragment extends InjectionFragment {
    public static final String TAG = MainHtmlQueryFragment.class.getSimpleName();

    @VisibleForTesting
    @Getter
    @Inject
    MainPageParser pageParser;

    public static MainHtmlQueryFragment instance() {
        return new MainHtmlQueryFragment();
    }

    public MainHtmlQueryFragment() {
        // Default Constructor
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

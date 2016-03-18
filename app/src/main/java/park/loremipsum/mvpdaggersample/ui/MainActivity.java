package park.loremipsum.mvpdaggersample.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import park.loremipsum.mvpdaggersample.R;
import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.model.TabMenu;
import park.loremipsum.mvpdaggersample.ui.castlist.CardListFragment;
import park.loremipsum.mvpdaggersample.ui.castlist.MainHtmlQueryFragment;
import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;

public class MainActivity extends InjectionActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    //region Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        eventBus.unregister(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    private void setupFragments() {
        final Fragment htmlQueryFragment = getSupportFragmentManager().findFragmentByTag(MainHtmlQueryFragment.TAG);
        if (htmlQueryFragment == null) {
            final MainHtmlQueryFragment fragment = MainHtmlQueryFragment.instance();
            getSupportFragmentManager().beginTransaction().add(fragment, MainHtmlQueryFragment.TAG).commit();
        }

        final Fragment cardListFragment = getSupportFragmentManager().findFragmentByTag(CardListFragment.TAG);
        if (cardListFragment == null) {
            final CardListFragment fragment = CardListFragment.instance();
            getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragment, CardListFragment.TAG).commit();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onFinishTabQuery(MainPageParser.TabMenuQueryEvent event) {
        final List<TabMenu> tabMenus = event.getTabMenus();
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        for (TabMenu tabMenu : tabMenus) {
            final String title = tabMenu.getTitle();
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int selectedPosition = tab.getPosition();
                final String absHrefLink = tabMenus.get(selectedPosition).getAbsHref();
                changeTab(absHrefLink);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.setVisibility(View.VISIBLE);
    }

    private void changeTab(String tabUrl) {
        final CardListFragment cardListFragment =
                (CardListFragment) getSupportFragmentManager().findFragmentByTag(CardListFragment.TAG);
        cardListFragment.showLoadingProgress();

        final MainHtmlQueryFragment mainHtmlQueryFragment =
                (MainHtmlQueryFragment) getSupportFragmentManager().findFragmentByTag(MainHtmlQueryFragment.TAG);
        mainHtmlQueryFragment.parseTab(tabUrl);
    }
}

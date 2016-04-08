package park.loremipsum.mvpdaggersample.ui.castlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.Bind;
import dagger.Lazy;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.R;
import park.loremipsum.mvpdaggersample.ui.common.BaseFragment;
import park.loremipsum.mvpdaggersample.util.dagger.component.FragmentComponent;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;

public class CardListFragment extends BaseFragment implements CardListPresenter.ViewInterface {
    public static final String TAG = CardListFragment.class.getSimpleName();

    @Inject
    EventBus bus;
    @Getter
    @Inject
    CardListPresenter presenter;

    @Inject
    Lazy<CastCardAdapter> lazyAdapter;
    @Inject
    Lazy<RecyclerView.LayoutManager> lazyLinearLayoutManager;

    @Bind(R.id.card_list)
    RecyclerView recyclerView;
    @Bind(R.id.card_list_loading_progress)
    View loadingProgress;

    private CastCardAdapter adapter;

    //region Factory
    public static CardListFragment instance() {
        return new CardListFragment();
    }

    public CardListFragment() {
        // Default Constructor
    }
    //endregion

    //endregion
    //region Life Cycle
    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.plusCardList().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = lazyAdapter.get();
        recyclerView.setLayoutManager(lazyLinearLayoutManager.get());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        presenter.init(adapter, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }
    //endregion

    //region ViewInterface
    @Override
    public void showProgress() {
        loadingProgress.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        loadingProgress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCast(String cardTitle, String cardUrl) {
//        startActivity(CardActivity.createIntent(getActivity(), cardTitle, cardUrl));
    }
    //endregion
}

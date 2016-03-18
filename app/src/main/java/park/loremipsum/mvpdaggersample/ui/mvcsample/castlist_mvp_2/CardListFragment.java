package park.loremipsum.mvpdaggersample.ui.mvcsample.castlist_mvp_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import park.loremipsum.mvpdaggersample.R;
import park.loremipsum.mvpdaggersample.ui.card.CardActivity;
import park.loremipsum.mvpdaggersample.ui.mvcsample.castlist_mvc.utils.EventBusProvider;

public class CardListFragment extends Fragment implements CardListPresenter.ViewInterface {
    public static final String TAG = CardListFragment.class.getSimpleName();

    @Bind(R.id.card_list)
    RecyclerView recyclerView;
    @Bind(R.id.card_list_loading_progress)
    View loadingProgress;

    private CardListPresenter presenter;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CardListPresenter.ListModelInterface listModelInterface = setupAdapter();
        presenter = new CardListPresenter(this, listModelInterface, savedInstanceState);
    }

    private CastCardAdapter setupAdapter() {
        final CastCardAdapter adapter = new CastCardAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        EventBusProvider.getInstance().unregister(this);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
    //endregion

    //region ViewInterface
    @Override
    public void showProgress() {
        loadingProgress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingProgress.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showCast(String cardTitle, String cardUrl) {
        startActivity(CardActivity.createIntent(getActivity(), cardTitle, cardUrl));
    }
    //endregion
}

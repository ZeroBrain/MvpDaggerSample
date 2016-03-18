package park.loremipsum.mvpdaggersample.ui.castlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import dagger.Lazy;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.R;
import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.ui.common.BaseFragment;
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
    @Bind(R.id.card_list_loading_text)
    TextView loadingText;

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

        recyclerView.setLayoutManager(lazyLinearLayoutManager.get());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(lazyAdapter.get());
        loadingText.setText("Loading...");
    }
    //endregion

    public void showLoadingProgress() {
        loadingText.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    //region ViewInterface
    @Override
    public void showCastCards(List<CastCard> castCards) {
        final CastCardAdapter adapter = (CastCardAdapter) recyclerView.getAdapter();
        adapter.replace(castCards);
        loadingText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void moveToCastCardDetail(int position) {
        final CastCard card = ((CastCardAdapter) recyclerView.getAdapter()).getItem(position);
        final String cardTitle = card.getCardTitle();
        final String cardUrl = card.getCategoryAbsHref();
//        startActivity(CardActivity.createIntent(getActivity(), cardTitle, cardUrl));
    }
    //endregion
}

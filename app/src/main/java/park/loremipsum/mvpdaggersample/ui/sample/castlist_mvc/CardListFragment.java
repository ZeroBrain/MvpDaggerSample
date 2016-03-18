package park.loremipsum.mvpdaggersample.ui.sample.castlist_mvc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import park.loremipsum.mvpdaggersample.R;
import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.ui.card.CardActivity;
import park.loremipsum.mvpdaggersample.ui.sample.castlist_mvc.utils.EventBusProvider;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapper;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapperImpl;

public class CardListFragment extends Fragment {
    public static final String TAG = CardListFragment.class.getSimpleName();

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new CastCardAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        if (savedInstanceState != null) {
            adapter.restoreSavedState(savedInstanceState);
            loadingProgress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            loadingProgress.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            CastCardFetchAsyncTask task = new CastCardFetchAsyncTask();
            task.execute();
        }
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
        adapter.onSaveInstanceState(outState);
    }
    //endregion

    @SuppressWarnings("unused")
    @Subscribe
    public void onClickCastCard(park.loremipsum.mvpdaggersample.ui.castlist.CastCardAdapter.CastCardViewHolder.OnClickEVent event) {
        final int position = event.getPosition();
        final CastCard card = ((CastCardAdapter) recyclerView.getAdapter()).getItem(position);
        final String cardTitle = card.getCardTitle();
        final String cardUrl = card.getCategoryAbsHref();
        startActivity(CardActivity.createIntent(getActivity(), cardTitle, cardUrl));
    }

    class CastCardFetchAsyncTask extends AsyncTask<Void, Void, List<CastCard>> {

        @Override
        protected List<CastCard> doInBackground(Void... params) {
            final Document document;
            try {
                final JsoupWrapper jsoupWrapper = new JsoupWrapperImpl();
                document = jsoupWrapper.get("http://navercast.naver.com");
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }

            final List<CastCard> castCards = new ArrayList<>();
            final Elements cardElements = document.select("div.card_list section");
            for (Element cardElement : cardElements) {
                final String cardId;
                final String cardTitle;
                final String descriptionText;
                final String authorName;
                final String authorAbsHref;
                final String categoryTitle;
                final String categoryAbsHref;
                final String thumbnailUrl;

                final Elements textBoxElements = cardElement.select("div.txt_box");
                if (textBoxElements.size() > 0) {
                    final Element textBoxElement = textBoxElements.get(0);
                    final Elements titleElements = textBoxElement.getElementsByClass("cds_h3");
                    final Elements descriptionElements = textBoxElement.getElementsByClass("txt");
                    cardId = cardElement.attr("id");
                    cardTitle = titleElements.size() > 0 ?
                            titleElements.get(0).text() : "";
                    descriptionText = descriptionElements.size() > 0 ?
                            descriptionElements.get(0).text() : "";
                } else {
                    continue;
                }

                final Elements authorElements = cardElement.select("span.name > a");
                if (authorElements.size() > 0) {
                    final Element authorElement = authorElements.get(0);
                    authorName = authorElement.text();
                    authorAbsHref = authorElement.attr("abs:href");
                } else {
                    authorName = "";
                    authorAbsHref = "";
                }

                final Elements categoryElements = cardElement.select("span.txt_cr > a");
                if (categoryElements.size() > 0) {
                    final Element categoryElement = categoryElements.get(0);
                    categoryTitle = categoryElement.text();
                    categoryAbsHref = categoryElement.attr("abs:href");
                } else {
                    categoryTitle = "";
                    categoryAbsHref = "";
                }

                final Elements thumbnailElements = cardElement.select("div.cds_addition > img");
                if (thumbnailElements.size() > 0) {
                    final Element thumbnailElement = thumbnailElements.get(0);

                    final String lazyUrl = thumbnailElement.attr("data-src");
                    if (TextUtils.isEmpty(lazyUrl)) {
                        thumbnailUrl = thumbnailElement.attr("src");
                    } else {
                        thumbnailUrl = lazyUrl;
                    }
                } else {
                    thumbnailUrl = "";
                }

                final CastCard card = new CastCard(cardId, cardTitle, descriptionText, authorName, authorAbsHref, categoryTitle, categoryAbsHref, thumbnailUrl);
                castCards.add(card);
            }
            return castCards;
        }

        @Override
        protected void onPostExecute(List<CastCard> castCards) {
            adapter.replace(castCards);
            loadingProgress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
    //endregion
}

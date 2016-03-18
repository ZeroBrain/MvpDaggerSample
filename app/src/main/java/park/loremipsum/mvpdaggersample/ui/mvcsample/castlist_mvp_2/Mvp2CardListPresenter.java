package park.loremipsum.mvpdaggersample.ui.mvcsample.castlist_mvp_2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;

import com.squareup.otto.Subscribe;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapper;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapperImpl;

public class Mvp2CardListPresenter {

    private final ViewInterface viewInterface;
    private final ListModelInterface listModelInterface;

    public Mvp2CardListPresenter(ViewInterface viewInterface,
                                 ListModelInterface listModelInterface,
                                 Bundle savedInstanceState) {
        this.viewInterface = viewInterface;
        this.listModelInterface = listModelInterface;
        if (savedInstanceState != null) {
            viewInterface.hideProgress();
            listModelInterface.restoreSavedState(savedInstanceState);
        } else {
            viewInterface.showProgress();
            fetchCardList();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        listModelInterface.onSaveInstanceState(outState);
    }

    public void fetchCardList() {
        final CastCardFetchAsyncTask task = new CastCardFetchAsyncTask();
        task.execute();
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onClickCastCard(park.loremipsum.mvpdaggersample.ui.castlist.CastCardAdapter.CastCardViewHolder.OnClickEVent event) {
        final int position = event.getPosition();
        CastCard cast = listModelInterface.getCastAtPosition(position);
        final String cardTitle = cast.getCardTitle();
        final String cardUrl = cast.getCategoryAbsHref();
        viewInterface.showCast(cardTitle, cardUrl);
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
            listModelInterface.addCardList(castCards);
            viewInterface.hideProgress();
        }
    }

    public interface ViewInterface {
        void showProgress();

        void hideProgress();

        void showCast(String cardTitle, String cardUrl);
    }

    public interface ListModelInterface {
        void onSaveInstanceState(Bundle outState);

        void restoreSavedState(Bundle savedInstanceState);

        void addCardList(List<CastCard> castCardList);

        CastCard getCastAtPosition(int position);
    }
}

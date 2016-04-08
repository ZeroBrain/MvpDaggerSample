package park.loremipsum.mvpdaggersample.ui.castlist;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.ui.common.BasePresenter;

public class CardListPresenter extends BasePresenter {

    @VisibleForTesting
    @Getter // UnitTest가 정상 동작하는지 보여주기 위한
    @Inject
    ViewInterface viewInterface;

    private ListModelInterface listModelInterface;

    @Inject
    public CardListPresenter() {
    }

    public void init(ListModelInterface listModelInterface, Bundle savedInstanceState) {
        this.listModelInterface = listModelInterface;
        if (savedInstanceState != null) {
            viewInterface.hideProgress();
            listModelInterface.restoreSavedState(savedInstanceState);
        } else {
            viewInterface.showProgress();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onFinishCastCardQuery(MainPageParser.CastCardQueryEvent event) {
        final List<CastCard> castCards = event.getCastCards();
        listModelInterface.addCardList(castCards);
        viewInterface.hideProgress();
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onClickCastCard(CastCardAdapter.CastCardViewHolder.OnClickEVent event) {
        final int position = event.getPosition();
        final CastCard cast = listModelInterface.getCastAtPosition(position);
        final String cardTitle = cast.getCardTitle();
        final String cardUrl = cast.getCategoryAbsHref();
        viewInterface.showCast(cardTitle, cardUrl);
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

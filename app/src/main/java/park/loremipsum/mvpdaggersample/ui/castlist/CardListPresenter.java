package park.loremipsum.mvpdaggersample.ui.castlist;

import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.ui.common.BasePresenter;

public class CardListPresenter extends BasePresenter {

    @Inject
    ViewInterface viewInterface;

    @Inject
    public CardListPresenter() {
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onFinishCastCardQuery(MainPageParser.CastCardQueryEvent event) {
        final List<CastCard> castCards = event.getCastCards();
        viewInterface.showCastCards(castCards);
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onClickCastCard(CastCardAdapter.CastCardViewHolder.OnClickEVent event) {
        final int position = event.getPosition();
        viewInterface.moveToCastCardDetail(position);
    }

    public interface ViewInterface {
        void showCastCards(List<CastCard> castCards);

        void moveToCastCardDetail(int position);
    }
}

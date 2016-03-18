package park.loremipsum.mvpdaggersample.ui.mvcsample.castlist_mvp_2;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.AllArgsConstructor;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.R;
import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.ui.mvcsample.castlist_mvc.utils.EventBusProvider;
import park.loremipsum.mvpdaggersample.ui.common.RecyclerListAdapter;
import park.loremipsum.mvpdaggersample.util.thirdparty.parceler.ParcelerImpl;

public class Mvp2CastCardAdapter extends RecyclerListAdapter<CastCard, Mvp2CastCardAdapter.CastCardViewHolder> implements Mvp2CardListPresenter.ListModelInterface {
    private static final String DATA_SET = "dataSet";

    private final Context context;

    public Mvp2CastCardAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        final ParcelerImpl parceler = new ParcelerImpl();
        outState.putParcelableArrayList(DATA_SET, parceler.parcel(getDataSet()));
    }

    @Override
    public void restoreSavedState(Bundle savedInstanceState) {
        final ArrayList<Parcelable> parceledList = savedInstanceState.getParcelableArrayList(DATA_SET);
        final ParcelerImpl parceler = new ParcelerImpl();
        addAll(parceler.unparcel(CastCard.class, parceledList));
    }

    @Override
    public void addCardList(List<CastCard> castCardList) {
        replace(castCardList);
    }

    @Override
    public CastCard getCastAtPosition(int position) {
        return getItem(position);
    }

    @Override
    public CastCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.list_item_card, parent, false);
        return new CastCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CastCardViewHolder holder, int position) {
        final CastCard card = getItem(position);
        final String thumbnailUrl = card.getThumbnailUrl();
        Glide.with(context).load(thumbnailUrl).into(holder.image);
        final String title = card.getCardTitle();
        holder.title.setText(title);
        final String subTitle = card.getSubTitle();
        holder.subTitle.setText(subTitle);
        final String author = card.getAuthorName();
        holder.description.setText(author);
    }

    public static class CastCardViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_card_image)
        ImageView image;
        @Bind(R.id.item_card_title)
        TextView title;
        @Bind(R.id.item_card_sub_title)
        TextView subTitle;
        @Bind(R.id.item_card_description)
        TextView description;

        public CastCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_card_view)
        void onClick() {
            final int position = getAdapterPosition();
            EventBusProvider.getInstance().post(new OnClickEVent(position));
        }

        @AllArgsConstructor(suppressConstructorProperties = true)
        public static class OnClickEVent {
            @Getter
            private final int position;
        }
    }
}

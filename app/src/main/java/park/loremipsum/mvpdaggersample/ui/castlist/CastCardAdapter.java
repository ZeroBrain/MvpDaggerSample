package park.loremipsum.mvpdaggersample.ui.castlist;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.AllArgsConstructor;
import lombok.Getter;
import park.loremipsum.mvpdaggersample.R;
import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.ui.common.RecyclerListAdapter;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;
import park.loremipsum.mvpdaggersample.util.thirdparty.glide.GlideWrapper;
import park.loremipsum.mvpdaggersample.util.thirdparty.parceler.Parceler;

public class CastCardAdapter extends RecyclerListAdapter<CastCard, CastCardAdapter.CastCardViewHolder> {
    private static final String DATA_SET = "dataSet";

    private final Parceler parceler;
    private final LayoutInflater layoutInflater;
    private final GlideWrapper glideWrapper;
    private final EventBus bus;

    @Inject
    public CastCardAdapter(Parceler parceler,
                           GlideWrapper glideWrapper,
                           LayoutInflater layoutInflater,
                           EventBus bus) {
        this.parceler = parceler;
        this.glideWrapper = glideWrapper;
        this.layoutInflater = layoutInflater;
        this.bus = bus;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(DATA_SET, parceler.parcel(getDataSet()));
    }

    public void restoreSavedState(Bundle savedInstanceState) {
        final ArrayList<Parcelable> parceledList = savedInstanceState.getParcelableArrayList(DATA_SET);
        addAll(parceler.unparcel(CastCard.class, parceledList));
    }

    @Override
    public CastCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.list_item_card, parent, false);
        return new CastCardViewHolder(view, bus);
    }

    @Override
    public void onBindViewHolder(CastCardViewHolder holder, int position) {
        final CastCard card = getItem(position);
        final String thumbnailUrl = card.getThumbnailUrl();
        glideWrapper.load(thumbnailUrl, holder.image);
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

        private final EventBus bus;

        public CastCardViewHolder(View itemView, EventBus bus) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.bus = bus;
        }

        @OnClick(R.id.item_card_view)
        void onClick() {
            final int position = getAdapterPosition();
            bus.post(new OnClickEVent(position));
        }

        @AllArgsConstructor(suppressConstructorProperties = true)
        public static class OnClickEVent {
            @Getter
            private final int position;
        }
    }
}

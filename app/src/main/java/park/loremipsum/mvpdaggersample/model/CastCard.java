package park.loremipsum.mvpdaggersample.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Getter;
import lombok.ToString;

@Parcel
@ToString
public class CastCard {
    @Getter
    final String cardId;
    @Getter
    final String cardTitle;
    @Getter
    final String subTitle;
    @Getter
    final String authorName;
    @Getter
    final String authorAbsHref;
    @Getter
    final String categoryTitle;
    @Getter
    final String categoryAbsHref;
    @Getter
    final String thumbnailUrl;

    @ParcelConstructor
    public CastCard(String cardId, String cardTitle, String subTitle, String authorName, String authorAbsHref, String categoryTitle, String categoryAbsHref, String thumbnailUrl) {
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.subTitle = subTitle;
        this.authorName = authorName;
        this.authorAbsHref = authorAbsHref;
        this.categoryTitle = categoryTitle;
        this.categoryAbsHref = categoryAbsHref;
        this.thumbnailUrl = thumbnailUrl;
    }
}
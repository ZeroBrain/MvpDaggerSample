package park.loremipsum.mvpdaggersample.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Getter;

@Parcel
public class TabMenu {
    @Getter
    final String title;

    @Getter
    final String absHref;

    @Getter
    final boolean isSelected;

    @ParcelConstructor
    public TabMenu(String title, String absHref, boolean isSelected) {
        this.title = title;
        this.absHref = absHref;
        this.isSelected = isSelected;
    }
}
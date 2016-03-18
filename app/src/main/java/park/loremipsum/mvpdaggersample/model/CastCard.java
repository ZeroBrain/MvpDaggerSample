package park.loremipsum.mvpdaggersample.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(suppressConstructorProperties = true)
public class CastCard {
    String cardId;
    String cardTitle;
    String subTitle;
    String authorName;
    String authorAbsHref;
    String categoryTitle;
    String categoryAbsHref;
    String thumbnailUrl;
}

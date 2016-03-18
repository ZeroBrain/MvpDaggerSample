package park.loremipsum.mvpdaggersample.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(suppressConstructorProperties = true)
public class TopMenu {
    String title;
    String absHref;
    boolean isSelected;
}

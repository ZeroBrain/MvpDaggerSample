package park.loremipsum.mvpdaggersample.ui.card;

import android.app.Activity;
import android.content.Intent;

import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
import park.loremipsum.mvpdaggersample.util.dagger.component.ActivityComponent;

/**
 * Created by hyunwoopark on 2016. 3. 18..
 */
public class CardActivity extends InjectionActivity {

    public static Intent createIntent(Activity activity, String cardTitle, String cardUrl) {
        return new Intent();
    }

    @Override
    protected void inject(ActivityComponent component) {
        // TODO
    }
}

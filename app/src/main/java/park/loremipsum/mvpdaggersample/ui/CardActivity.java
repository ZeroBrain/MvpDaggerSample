package park.loremipsum.mvpdaggersample.ui;

import android.app.Activity;
import android.content.Intent;

import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;

/**
 * Created by hyunwoopark on 2016. 3. 18..
 */
public class CardActivity extends InjectionActivity {

    public static Intent createIntent(Activity activity, String cardTitle, String cardUrl) {
        return new Intent();
    }
}

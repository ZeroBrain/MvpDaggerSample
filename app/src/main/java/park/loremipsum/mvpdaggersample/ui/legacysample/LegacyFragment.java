package park.loremipsum.mvpdaggersample.ui.legacysample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by hyunwoopark on 2016. 4. 8..
 */
public class LegacyFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 레거시 코드에서 싱글톤으로 사용하는 건들도 Dagger를 통해 획득할 수 있다
        LegacyModel legacyModel = LegacyModel.getInstance();
    }
}

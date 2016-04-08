package park.loremipsum.mvpdaggersample.ui.legacysample;

import javax.inject.Inject;
import javax.inject.Singleton;

import park.loremipsum.mvpdaggersample.InjectionApplication;

/**
 * Legacy 코드에서 싱글톤으로 사용되고 있는 클래스에 대한 처리
 * Created by hyunwoopark on 2016. 4. 8..
 */
@Singleton
public class LegacyModel {
    //public static LegacyModel legacyModel = new LegacyModel();
    public static LegacyModel getInstance() {
        return InjectionApplication.getComponent().getLegacyModel();
    }

    @Inject
    public LegacyModel() {
    }
}

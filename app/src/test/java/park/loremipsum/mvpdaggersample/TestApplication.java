package park.loremipsum.mvpdaggersample;

import android.support.annotation.VisibleForTesting;

import park.loremipsum.mvpdaggersample.dagger.injector.TestInjectorCreator;
import park.loremipsum.mvpdaggersample.util.dagger.injector.InjectorCreator;

/**
 * Created by hyunwoopark on 2016. 4. 8..
 */
public class TestApplication extends InjectionApplication {

    @VisibleForTesting
    protected InjectorCreator makeInjectorCreator() {
        return new TestInjectorCreator();
    }

    @VisibleForTesting
    public void changeInjector(InjectorCreator injectorCreator) {
        this.injectorCreator = injectorCreator;
        inject();
    }
}

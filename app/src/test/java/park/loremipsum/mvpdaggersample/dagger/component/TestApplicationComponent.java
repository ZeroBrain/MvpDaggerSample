package park.loremipsum.mvpdaggersample.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.dagger.module.TestApplicationModule;
import park.loremipsum.mvpdaggersample.dagger.module.TestParserModule;
import park.loremipsum.mvpdaggersample.util.dagger.ActivityModule;
import park.loremipsum.mvpdaggersample.util.dagger.component.ApplicationComponent;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBusModule;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupModule;
import park.loremipsum.mvpdaggersample.util.thirdparty.parceler.ParcelerModule;

@Singleton
@Component(modules = {TestApplicationModule.class,
        TestParserModule.class,
        EventBusModule.class,
        JsoupModule.class,
        ParcelerModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

    /**
     * Subcomponents
     */
    TestActivityComponent plusTestActivityComponent(ActivityModule activityModule);

    /**
     * Members-injection methods
     */
    void inject(InjectionApplication application);
}

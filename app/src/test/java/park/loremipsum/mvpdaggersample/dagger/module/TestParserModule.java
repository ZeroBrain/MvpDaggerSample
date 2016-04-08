package park.loremipsum.mvpdaggersample.dagger.module;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.dagger.injector.TestInjectorCreator;
import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapper;

/**
 * Created by hyunwoopark on 2016. 4. 8..
 */
@Module
public class TestParserModule {
    @Provides
    @Singleton
    MainPageParser provideMainPageParser(JsoupWrapper jsoupWrapper, EventBus bus) {
        for (Object object : TestInjectorCreator.mockList) {
            if (object instanceof MainPageParser) {
                return (MainPageParser) object;
            }
        }
        return new MainPageParser(jsoupWrapper, bus);
    }
}

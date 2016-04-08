package park.loremipsum.mvpdaggersample.dagger.module;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapper;

/**
 * Created by hyunwoopark on 2016. 4. 8..
 */
@Module
public class TestParserModule {
    private final List<Object> mockList;

    public TestParserModule(List<Object> mockList) {
        this.mockList = mockList;
    }

    @Provides
    @Singleton
    MainPageParser provideMainPageParser(JsoupWrapper jsoupWrapper, EventBus bus) {
        for (Object object : mockList) {
            if (object instanceof MainPageParser) {
                return (MainPageParser) object;
            }
        }
        return new MainPageParser(jsoupWrapper, bus);
    }
}

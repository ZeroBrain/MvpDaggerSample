package park.loremipsum.mvpdaggersample.domain.castparser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapper;

@Module
public class ParserModule {
    @Provides
    @Singleton
    MainPageParser provideMainPageParser(JsoupWrapper jsoupWrapper, EventBus bus) {
        return new MainPageParser(jsoupWrapper, bus);
    }
}
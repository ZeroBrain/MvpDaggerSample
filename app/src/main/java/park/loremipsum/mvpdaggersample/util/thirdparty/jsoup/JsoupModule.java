package park.loremipsum.mvpdaggersample.util.thirdparty.jsoup;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class JsoupModule {
    @Provides
    @Singleton
    JsoupWrapper provideJsoupWrapper() {
        return new JsoupWrapperImpl();
    }
}
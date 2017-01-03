package park.loremipsum.mvpdaggersample.util.dagger;

import javax.inject.Singleton;

import android.content.Context;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.domain.castparser.ParserModule;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBusModule;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupModule;
import park.loremipsum.mvpdaggersample.util.thirdparty.parceler.ParcelerModule;

@Module(includes = {ParserModule.class,
	EventBusModule.class,
	JsoupModule.class,
	ParcelerModule.class})
public class ApplicationModule {
	private final InjectionApplication application;

	public ApplicationModule(InjectionApplication application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Context provideApplicationContext() {
		return application;
	}

	@Provides
	@Singleton
	Resources provideResources() {
		return application.getResources();
	}
}

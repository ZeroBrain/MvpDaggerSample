package park.loremipsum.mvpdaggersample.util.thirdparty.parceler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ParcelerModule {
    @Provides
    @Singleton
    Parceler providerParceler() {
        return new ParcelerImpl();
    }
}

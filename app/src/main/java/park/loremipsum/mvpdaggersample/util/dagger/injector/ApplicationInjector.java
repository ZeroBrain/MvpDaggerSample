package park.loremipsum.mvpdaggersample.util.dagger.injector;

import lombok.Getter;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.util.dagger.component.ApplicationComponent;

public class ApplicationInjector {

    @Getter
    private final ApplicationComponent applicationComponent;

    public ApplicationInjector(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public void inject(InjectionApplication application) {
        applicationComponent.inject(application);
    }
}

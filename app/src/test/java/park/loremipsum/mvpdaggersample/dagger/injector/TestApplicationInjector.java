//package park.loremipsum.mvpdaggersample.dagger.injector;
//
//import lombok.Getter;
//import park.loremipsum.mvpdaggersample.InjectionApplication;
//import park.loremipsum.mvpdaggersample.dagger.component.TestApplicationComponent;
//import park.loremipsum.mvpdaggersample.util.dagger.injector.ApplicationInjector;
//
//public class TestApplicationInjector extends ApplicationInjector {
//
//    @Getter
//    private final TestApplicationComponent applicationComponent;
//
//    public TestApplicationInjector(TestApplicationComponent applicationComponent) {
//        super(applicationComponent);
//        this.applicationComponent = applicationComponent;
//    }
//
//    public void inject(InjectionApplication application) {
//        applicationComponent.inject(application);
//    }
//}

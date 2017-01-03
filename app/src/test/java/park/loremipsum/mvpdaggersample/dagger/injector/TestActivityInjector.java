//package park.loremipsum.mvpdaggersample.dagger.injector;
//
//import lombok.Getter;
//import park.loremipsum.mvpdaggersample.dagger.component.TestActivityComponent;
//import park.loremipsum.mvpdaggersample.ui.MainActivity;
//import park.loremipsum.mvpdaggersample.util.dagger.InjectionActivity;
//import park.loremipsum.mvpdaggersample.util.dagger.injector.ActivityInjector;
//
//public class TestActivityInjector extends ActivityInjector {
//
//    @Getter
//    private final TestActivityComponent testActivityComponent;
//
//    public TestActivityInjector(TestActivityComponent testActivityComponent) {
//        super(testActivityComponent);
//        this.testActivityComponent = testActivityComponent;
//    }
//
//    public void inject(InjectionActivity activity) {
//        if (activity instanceof MainActivity) {
//            testActivityComponent.inject((MainActivity) activity);
//        }
//    }
//}

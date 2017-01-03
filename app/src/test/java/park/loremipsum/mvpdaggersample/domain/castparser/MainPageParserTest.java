//package park.loremipsum.mvpdaggersample.domain.castparser;
//
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.robolectric.RobolectricGradleTestRunner;
//import org.robolectric.annotation.Config;
//
//import park.loremipsum.mvpdaggersample.BuildConfig;
//import park.loremipsum.mvpdaggersample.TestApplication;
//import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;
//import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapper;
//
///**
// * 손으로 정성스럽게 Injection
// * Created by hyunwoopark on 2016. 4. 8..
// */
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
//public class MainPageParserTest {
//
//    MainPageParser mainPageParser;
//
//    @Mock
//    EventBus eventBus;
//    @Mock
//    JsoupWrapper jsoupWrapper;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mainPageParser = new MainPageParser(jsoupWrapper, eventBus);
//    }
//
//    @Test
//    public void test() throws Exception {
//        // TODO
//    }
//}

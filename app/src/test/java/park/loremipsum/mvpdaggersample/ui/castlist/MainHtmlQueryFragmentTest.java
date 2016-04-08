package park.loremipsum.mvpdaggersample.ui.castlist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import park.loremipsum.mvpdaggersample.BuildConfig;
import park.loremipsum.mvpdaggersample.TestApplication;
import park.loremipsum.mvpdaggersample.dagger.injector.TestInjectorCreator;
import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
import park.loremipsum.mvpdaggersample.ui.MainActivity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Application의 InjectorCreator를 변경하여 Injection
 * Created by hyunwoopark on 2016. 4. 8..
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class MainHtmlQueryFragmentTest {
    @Mock
    MainPageParser parser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        final TestInjectorCreator injectorCreator = new TestInjectorCreator();

        final List<Object> mockList = new ArrayList<>();
        mockList.add(parser);
        injectorCreator.addMock(mockList);

        final TestApplication testApplication = (TestApplication) RuntimeEnvironment.application;
        testApplication.changeInjectorCreator(injectorCreator);
    }

    @Test
    public void test() throws Exception {
        final ActivityController<MainActivity> controller
                = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .visible(); // 이 시점부터 View와 interacting 가능
        final MainActivity activity = controller.get();

        MainHtmlQueryFragment fragment = (MainHtmlQueryFragment) activity.getSupportFragmentManager().findFragmentByTag(MainHtmlQueryFragment.TAG);
        assertThat("MainHtmlQueryFragment Fragment 생성", fragment, notNullValue());
        assertThat("Mock Test", fragment.getPageParser(), is(parser));
    }
}

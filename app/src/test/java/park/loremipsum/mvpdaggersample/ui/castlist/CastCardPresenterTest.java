package park.loremipsum.mvpdaggersample.ui.castlist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dagger.Component;
import dagger.Provides;
import park.loremipsum.mvpdaggersample.BaseTestSupport;
import park.loremipsum.mvpdaggersample.BuildConfig;
import park.loremipsum.mvpdaggersample.TestApplication;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * 직접 Component를 만들어서 Injection
 *
 * Created by hyunwoopark on 2016. 4. 8..
 */
public class CastCardPresenterTest extends BaseTestSupport {

    @Component(modules = CastCardComponent.TestModule.class)
    interface CastCardComponent {

        void inject(CardListPresenter presenter);

        @dagger.Module
        class TestModule {
            private final CardListPresenter.ViewInterface viewInterface;
            private final EventBus eventBus;

            public TestModule(CardListPresenter.ViewInterface viewInterface, EventBus eventBus) {
                this.viewInterface = viewInterface;
                this.eventBus = eventBus;
            }

            @Provides
            public CardListPresenter.ViewInterface provideViewInterface() {
                return viewInterface;
            }

            @Provides
            public EventBus provideEventBus() {
                return eventBus;
            }
        }
    }

    @Mock
    CardListPresenter.ViewInterface viewInterface;
    @Mock
    EventBus eventBus;

    CardListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        presenter = new CardListPresenter();
        DaggerCastCardPresenterTest_CastCardComponent
                .builder()
                .testModule(new CastCardComponent.TestModule(viewInterface, eventBus))
                .build()
                .inject(presenter);
    }

    @Test
    public void testName() throws Exception {
        assertThat("Mock Test", viewInterface, is(presenter.getViewInterface()));
    }
}

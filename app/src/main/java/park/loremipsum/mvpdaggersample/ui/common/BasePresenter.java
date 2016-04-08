package park.loremipsum.mvpdaggersample.ui.common;

import javax.inject.Inject;

import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;

public class BasePresenter {

    // UnitTest 예제를 위해 추가
    @Inject
    protected EventBus eventBus;
}

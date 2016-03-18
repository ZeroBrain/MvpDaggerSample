package park.loremipsum.mvpdaggersample.ui.sample.castlist_mvc.utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;

/**
 * Created by hyunwoopark on 2016. 3. 18..
 */
public class EventBusProvider {

    private static volatile EventBus eventBus;
    public static synchronized EventBus getInstance() {
        if (eventBus != null) {
            eventBus = new EventBus(new Bus(ThreadEnforcer.ANY));
        }
        return eventBus;
    }
}

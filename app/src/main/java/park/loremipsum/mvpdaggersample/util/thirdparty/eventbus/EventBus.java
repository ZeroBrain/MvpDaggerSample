package park.loremipsum.mvpdaggersample.util.thirdparty.eventbus;

import javax.inject.Inject;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventBus {
	private final Bus bus;

	@Inject
	public EventBus(Bus bus) {
		this.bus = bus;
	}

	public void post(final Object event) {
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				bus.post(event);
			}
		});
	}

	public void register(final Object object) {
		try {
			bus.register(object);
		} catch (IllegalArgumentException e) {
			log.error("Already registered object!");
		}
	}

	public void unregister(final Object object) {
		try {
			bus.unregister(object);
		} catch (IllegalArgumentException e) {
			log.error("You can not unregister no registered object!");
		}
	}
}
package park.loremipsum.mvpdaggersample.util.thirdparty.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class MainThreadExecutor implements Executor {
    private final Handler handler;

    @Inject
    public MainThreadExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        handler.post(runnable);
    }
}


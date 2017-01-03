package park.loremipsum.mvpdaggersample.util.dagger;

/**
 * Created by hyunwoopark on 2017. 1. 3..
 */

public interface ActivityComponentBuilder<V> {
	V build();

	ActivityComponentBuilder activityModule(ActivityModule activityModule);
}
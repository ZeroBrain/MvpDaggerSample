package park.loremipsum.mvpdaggersample.util.dagger;

/**
 * Created by hyunwoopark on 2017. 1. 3..
 */

public interface FragmentComponentBuilder<V> {
	V build();

	FragmentComponentBuilder fragmentModule(FragmentModule fragmentModule);
}
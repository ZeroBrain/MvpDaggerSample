package park.loremipsum.mvpdaggersample.util.dagger;

import java.util.Map;

import javax.inject.Provider;

/**
 * Created by hyunwoopark on 2017. 1. 3..
 */

public interface ActivityComponent {
	// MainHtmlQueryFragmentComponent Builders
	Map<Class<?>, Provider<FragmentComponentBuilder>> fragmentComponentBuilderMap();
}
package park.loremipsum.mvpdaggersample.util.dagger;

import java.util.Map;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Component;
import park.loremipsum.mvpdaggersample.InjectionApplication;
import park.loremipsum.mvpdaggersample.ui.legacysample.LegacyModel;

@Singleton
@Component(modules = {ApplicationModule.class, ActivityComponentBinder.class})
public interface ApplicationComponent {

    // ActivityComponent Builders
    Map<Class<?>, Provider<ActivityComponentBuilder>> activityComponentBuilderMap();

    /**
     * Members-injection methods
     * ApplicationComponent를 이용해 Members-injection할 클래스들은 여기에 정의되어야 합니다
     */
    void inject(InjectionApplication application);

    /**
     * Provision Methods
     * <p/>
     * Component Dependency gives you access to only the bindings exposed as provision methods through component dependencies, i.e.
     * you have access to only types which are declared in parent Component.
     * <p/>
     * ApplicationComponent의 모듈들의 객체들(또는 @Inject @Singleton 어노테이트된 생성자를 가진 객체들)에
     * 자식 Component가 접근하기 위해서
     * 또는 ApplicationComponent에서 직접 의존을 획득하기 위해서는 (ex. Resources resources = applicationComponent.resource(); )
     * Provision method로 선언되어야 합니다
     */
    LegacyModel getLegacyModel();
}

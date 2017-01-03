package park.loremipsum.mvpdaggersample.util.dagger.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by hyunwoopark on 2017. 1. 3..
 */
@MapKey
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentBindKey {
	Class<?> value();
}
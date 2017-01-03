//package park.loremipsum.mvpdaggersample.dagger.injector;
//
//import org.junit.Test;
//
//import java.lang.reflect.Type;
//import java.util.Map;
//
//import park.loremipsum.mvpdaggersample.BaseTestSupport;
//import park.loremipsum.mvpdaggersample.domain.castparser.MainPageParser;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.mock;
//
///**
// * Created by hyunwoopark on 2016. 4. 8..
// */
//public class TestInjectorCreatorTest extends BaseTestSupport {
//    @Test
//    public void testMockHolder() throws Exception {
//        MockHolder holder = new MockHolder();
//        MainPageParser mockPageParser = mock(MainPageParser.class);
//        holder.add(MainPageParser.class, mockPageParser);
//
//        Map<Type, Object> map = holder.getMockMap();
//        for (Map.Entry<Type, Object> entry : map.entrySet()) {
//            System.out.println("Class - " + entry.getKey().toString() + "\n Object - " + entry.getValue().toString() + "\n");
//        }
//
//        assertThat("Exist", holder.isExist(MainPageParser.class));
//        MainPageParser mainPageParserFromHolder = holder.get(MainPageParser.class);
//        assertThat("Same?", mockPageParser, is(mainPageParserFromHolder));
//    }
//}

package park.loremipsum.mvpdaggersample.util.thirdparty.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupWrapperImpl implements JsoupWrapper {
    @Override
    public Document get(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}

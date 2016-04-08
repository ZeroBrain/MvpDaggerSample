package park.loremipsum.mvpdaggersample.domain.castparser;

import android.text.TextUtils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import park.loremipsum.mvpdaggersample.model.CastCard;
import park.loremipsum.mvpdaggersample.model.TabMenu;
import park.loremipsum.mvpdaggersample.model.TopMenu;
import park.loremipsum.mvpdaggersample.util.dagger.qualifier.PerFragment;
import park.loremipsum.mvpdaggersample.util.thirdparty.eventbus.EventBus;
import park.loremipsum.mvpdaggersample.util.thirdparty.jsoup.JsoupWrapper;

@Slf4j
@PerFragment
public class MainPageParser {
    private final JsoupWrapper jsoupWrapper;
    private final EventBus bus;

    @Inject
    public MainPageParser(JsoupWrapper jsoupWrapper, EventBus bus) {
        this.jsoupWrapper = jsoupWrapper;
        this.bus = bus;
    }

    public void parseMainUrl() {
        final Document document;
        try {
            document = jsoupWrapper.get("http://navercast.naver.com");
        } catch (IOException e) {
            return;
        }

        final List<TopMenu> topMenus = parseTopMenu(document);
        bus.post(new TopMenuQueryEvent(topMenus));

        final List<TabMenu> tabMenus = parseTapMenus(document);
        bus.post(new TabMenuQueryEvent(tabMenus));

        final List<CastCard> castCards = parseCastCards(document);
        bus.post(new CastCardQueryEvent(castCards));
    }

    public void parseTab(String tabUrl) {
        final Document document;
        try {
            document = jsoupWrapper.get(tabUrl);
        } catch (IOException e) {
            return;
        }

        final List<CastCard> castCards = parseCastCards(document);
        bus.post(new CastCardQueryEvent(castCards));
    }

    private void parseOgProperty(Document document) {
        // title/네이버캐스트
        // type/article
        // url/http://navercast.naver.com/mobile_main.nhn?
        // image/http://static.naver.net/ncc/navercast/img/navercast.png
        // description/1년 365일, 다채로운 생활, 문화, 지식 콘텐츠가 네이버캐스트에 소개됩니다. 네이버캐스트는 네이버가 직접 기획하는 생활·문화·지식 콘텐츠를 제공합니다. 콘텐츠 제작을 위해 교육인, 문필가, 기자, 과학자 등 다양한 분야의 전문가들이 참여하고 있습니다.
        // article:author/
        // article:author:url/http://m.navercast.naver.com
        final Elements elements = document.select("meta[property^=og:]");
        Map<String, String> values = new HashMap<>();
        for (Element element : elements) {
            String property = element.attr("property");
            property = property.replace("og:", "");
            String content = element.attr("content");
            values.put(property, content);
        }
    }

    private List<TopMenu> parseTopMenu(Document document) {
        // Menu 일간 Top 20 / 건축기행 / 게임의 세계 ....
        final List<TopMenu> topMenus = new ArrayList<>();
        final Elements menuElements = document.select("div.dd_menu_t2 ul li");
        for (Element menuElement : menuElements) {
            final boolean isSelected = menuElement.hasClass("on");
            final Elements aHrefElements = menuElement.select("a[href]");
            if (aHrefElements.size() > 0) {
                final Element aHrefElement = aHrefElements.get(0);
                final String menuTitle = aHrefElement.text();
                final String absHref = aHrefElement.attr("abs:href");
                topMenus.add(new TopMenu(menuTitle, absHref, isSelected));
            }
        }
        return topMenus;
    }

    private List<TabMenu> parseTapMenus(Document document) {
        // Tab : 최신 / 조회 / 의견 / 좋아요
        final List<TabMenu> tabMenus = new ArrayList<>();
        final Elements tabElements = document.select("ul.sorting_t1 li");
        for (Element tabElement : tabElements) {
            final boolean isSelected = tabElement.hasClass("on");
            final Elements aHrefElements = tabElement.select("a[href]");
            if (aHrefElements.size() > 0) {
                final Element aHrefElement = aHrefElements.get(0);
                final String tabTitle = aHrefElement.text();
                final String absHref = aHrefElement.attr("abs:href");
                tabMenus.add(new TabMenu(tabTitle, absHref, isSelected));
            }
        }
        return tabMenus;
    }

    private List<CastCard> parseCastCards(Document document) {
        // CastCard
        final List<CastCard> castCards = new ArrayList<>();
        final Elements cardElements = document.select("div.card_list section");
        for (Element cardElement : cardElements) {
            final String cardId;
            final String cardTitle;
            final String descriptionText;
            final String authorName;
            final String authorAbsHref;
            final String categoryTitle;
            final String categoryAbsHref;
            final String thumbnailUrl;

            final Elements textBoxElements = cardElement.select("div.txt_box");
            if (textBoxElements.size() > 0) {
                final Element textBoxElement = textBoxElements.get(0);
                final Elements titleElements = textBoxElement.getElementsByClass("cds_h3");
                final Elements descriptionElements = textBoxElement.getElementsByClass("txt");
                cardId = cardElement.attr("id");
                cardTitle = titleElements.size() > 0 ?
                        titleElements.get(0).text() : "";
                descriptionText = descriptionElements.size() > 0 ?
                        descriptionElements.get(0).text() : "";
            } else {
                continue;
            }

            final Elements authorElements = cardElement.select("span.name > a");
            if (authorElements.size() > 0) {
                final Element authorElement = authorElements.get(0);
                authorName = authorElement.text();
                authorAbsHref = authorElement.attr("abs:href");
            } else {
                authorName = "";
                authorAbsHref = "";
            }

            final Elements categoryElements = cardElement.select("span.txt_cr > a");
            if (categoryElements.size() > 0) {
                final Element categoryElement = categoryElements.get(0);
                categoryTitle = categoryElement.text();
                categoryAbsHref = categoryElement.attr("abs:href");
            } else {
                categoryTitle = "";
                categoryAbsHref = "";
            }

            final Elements thumbnailElements = cardElement.select("div.cds_addition > img");
            if (thumbnailElements.size() > 0) {
                final Element thumbnailElement = thumbnailElements.get(0);

                final String lazyUrl = thumbnailElement.attr("data-src");
                if (TextUtils.isEmpty(lazyUrl)) {
                    thumbnailUrl = thumbnailElement.attr("src");
                } else {
                    thumbnailUrl = lazyUrl;
                }
            } else {
                thumbnailUrl = "";
            }

            final CastCard card = new CastCard(cardId, cardTitle, descriptionText, authorName, authorAbsHref, categoryTitle, categoryAbsHref, thumbnailUrl);
            log.info(card.toString());
            castCards.add(card);
        }
        return castCards;
    }

    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class TopMenuQueryEvent {
        @Getter
        private final List<TopMenu> topMenus;
    }

    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class TabMenuQueryEvent {
        @Getter
        private final List<TabMenu> tabMenus;
    }

    @AllArgsConstructor(suppressConstructorProperties = true)
    public static class CastCardQueryEvent {
        @Getter
        private final List<CastCard> castCards;
    }
}
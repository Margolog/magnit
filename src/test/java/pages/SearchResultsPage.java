package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {

    //Elements
    private ElementsCollection goods = $$("[data-test-id='v-stack-item']");
    private SelenideElement cardLink = $("a[href*='/product/']");

    //Actions
    public SearchResultsPage shouldHaveGoods(Integer size) {
        goods.shouldHave(sizeGreaterThan(size));
        return this;
    }

    public SearchResultsPage checkCardLink(String expectedLink) {
        cardLink.shouldHave(attributeMatching(
                "href",
                ".*" + expectedLink + ".*"));

        return this;
    }
}
package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {

    // Elements
    private final ElementsCollection goods =
            $$("[data-test-id='v-stack-item']");

    private final SelenideElement cardLink =
            $("a[href*='/product/']");

    // Actions
    @Step("Проверить, что список товаров содержит больше {size} товаров")
    public SearchResultsPage shouldHaveGoods(Integer size) {
        goods.shouldHave(sizeGreaterThan(size));
        return this;
    }

    @Step("Проверить, что ссылка карточки содержит: {expectedLink}")
    public SearchResultsPage checkCardLink(String expectedLink) {
        cardLink.shouldHave(attributeMatching(
                "href",
                ".*" + expectedLink + ".*"));

        return this;
    }
}
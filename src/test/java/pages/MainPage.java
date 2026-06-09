package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {


    private final SelenideElement        searchInput = $("[data-test-id='search-row']");
    private final SelenideElement        catalogButton = $(byText("Каталог"));
    private final SelenideElement        actionsLink = $(byText("Акции"));

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Ввести товар в поиск: {value}")
    public MainPage searchGoods(String value) {
        searchInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Проверить отображение пункта меню: {menuItem}")
    public MainPage checkMenuItemVisible(String menuItem) {
        $(byText(menuItem)).shouldBe(visible);
        return this;
    }

    @Step("Открыть каталог")
    public MainPage openCatalog() {
        catalogButton.click();
        return this;
    }

    @Step("Проверить, что каталог открыт")
    public MainPage checkCatalogOpened() {
        $(byText("Каталог")).shouldBe(visible);
        return this;
    }

    @Step("Открыть страницу акций")
    public MainPage openActionsPage() {
        actionsLink.click();
        return this;
    }
}
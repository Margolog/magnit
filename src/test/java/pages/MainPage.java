package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    //Elements
    private SelenideElement searchInput = $("[data-test-id='search-row']");

    //Actions
    public MainPage openPage() {
        open("https://magnit.ru/");
        return this;
    }

    public MainPage typeGoods(String value) {
        searchInput.setValue(value);
        return this;
    }

    public MainPage checkMenuItemVisible(String menuItem) {
        $(byText(menuItem)).shouldBe(visible);
        return this;
    }

}
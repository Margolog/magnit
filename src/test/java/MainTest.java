import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;

public class MainTest extends BaseTest {

    @ValueSource(strings = {
            "Акции",
            "Магазины",
            "Бонусы от партнёров",
            "Помощь",
            "Партнёрам",
            "О компании"
    })
    @ParameterizedTest(name = "Пункт меню {0} должен отображаться")
    void headerMenuItemsShouldBeVisible(String menuItem) {
        step("Открыть главную страницу", () -> {
            mainPage.openPage();
        });

        step("Проверить, что пункт меню отображается: " + menuItem, () -> {
            mainPage.checkMenuItemVisible(menuItem);
        });
    }

    @ValueSource(strings = {
            "Ананас",
            "сок",
            "сыР"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отображаться не пустой список товаров")
    void searchGoodsTest(String value) {
        step("Открыть главную страницу", () -> {
            mainPage.openPage();
        });

        step("Ввести поисковый запрос: " + value, () -> {
            mainPage.searchGoods(value);
        });

        step("Проверить, что список товаров не пустой", () -> {
            searchResultsPage.shouldHaveGoods(0);
        });
    }

    @CsvSource({
            "Ананас, /product/",
            "Сок, /product/",
            "Сыр, /product/"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} первая карточка должна содержать ссылку {1}")
    void searchResultsShouldContainExpectedUrl(String value, String expectedUrl) {
        step("Открыть главную страницу", () -> {
            mainPage.openPage();
        });

        step("Ввести поисковый запрос: " + value, () -> {
            mainPage.searchGoods(value);
        });

        step("Проверить, что первая карточка содержит ссылку: " + expectedUrl, () -> {
            searchResultsPage.checkCardLink(expectedUrl);
        });
    }
}
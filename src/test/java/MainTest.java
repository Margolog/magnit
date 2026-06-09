import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MainTest extends BaseTest {

    @ValueSource(strings = {"Ананас", "сок", "сыР"})
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список товаров")
    void searchGoodsTest(String value) {
        mainPage.openPage()
                .typeGoods(value);

        searchResultsPage.shouldHaveGoods(0);

    }

    @CsvSource(value = {
            "Ананас | /product/",
            "Сок | /product/",
            "Сыр | /product/"
    }, delimiter = '|')
    @ParameterizedTest(name = "Для поискового запроса {0} первая карточка должна содержать ссылку {1}")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        mainPage.openPage()
                .typeGoods(searchQuery);

        searchResultsPage.checkCardLink(expectedLink);
    }

    @ParameterizedTest(name = "Пункт меню {0} должен отображаться")
    @CsvFileSource(resources = "headerMenuItems/headerMenuItems.csv")
    void headerMenuItemsShouldBeVisible(String menuItem) {
        mainPage.openPage()
                .checkMenuItemVisible(menuItem);
    }
}


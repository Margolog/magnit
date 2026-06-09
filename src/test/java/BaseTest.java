import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;
import pages.SearchResultsPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    MainPage mainPage= new MainPage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();


    @BeforeAll
    static void setup() {

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserResolution", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless","true"));
        Configuration.timeout = 50000;
        Configuration.pageLoadTimeout = 60000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.savePageSource = true;
        Configuration.screenshots = true;
        Configuration.remote = System.getProperty("remote");

        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            String[] possiblePaths = {
                    "/usr/bin/chromedriver",
                    "/usr/local/bin/chromedriver",
                    "/snap/bin/chromedriver"
            };

            for (String path : possiblePaths) {
                if (new java.io.File(path).exists()) {
                    System.setProperty("webdriver.chrome.driver", path);
                    break;
                }
            }
        }

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}

//    @BeforeAll
//    static void setup() {
//        Configuration.baseUrl = "https://multicards.io";
//        Configuration.browserSize = "1920x1080";
//
//        String remoteUrl = System.getProperty("remoteUrl");
//        if (remoteUrl != null) {
//            Configuration.remote = remoteUrl;
//        }
//
//        SelenideLogger.addListener("allure", new AllureSelenide());
//    }
//
//    @AfterEach
//    void addAttachments() {
//        Attach.screenshotAs("Screenshot");
//        Attach.pageSource();
//        Attach.browserConsoleLogs();
//        closeWebDriver();
//
//    }


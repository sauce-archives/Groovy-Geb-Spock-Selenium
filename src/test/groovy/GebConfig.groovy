import geb.driver.SauceLabsDriverFactory
import groovy.json.JsonSlurper
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile

/*driver = {
    if (capabilities) {
        def username = System.getenv("SAUCE_USERNAME")
        assert username
        def accessKey = System.getenv("SAUCE_ACCESS_KEY")
        assert accessKey
        new SauceLabsDriverFactory().create(username, accessKey, capMap)
    } else {
        FirefoxProfile profile = new FirefoxProfile()
        new FirefoxDriver(profile)
    }
}*/
driver = {}

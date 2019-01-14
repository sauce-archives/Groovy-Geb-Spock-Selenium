import geb.driver.CachingDriverFactory
import geb.spock.GebSpec

class BasePageGebSpec extends GebSpec {
    @Override
    public void cleanup() throws Exception {
        driver.quit()
        CachingDriverFactory.clearCache()
    }
}
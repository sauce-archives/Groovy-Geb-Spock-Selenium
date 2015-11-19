package Spec

/**
 * Created by mehmetgerceker on 11/17/15.
 */
import Pages.BingResultPage
import Pages.YahooQueryPage
import Pages.YahooResultPage
import geb.driver.CachingDriverFactory

class YahooSpec extends SauceSpecBase {

    def setup() {
        //setup goes here
    }

    def cleanup() {
        CachingDriverFactory.clearCache()
        driver.quit()
    }

    def "Search \"hello!\""() {
        String q = "hello!"
        YahooResultPage.searchWord = q

        when:
        to YahooQueryPage

        and:
        search(q)

        then:
        waitFor {at YahooResultPage}
    }
    def "Search blank"() {
        String q = ""
        BingResultPage.searchWord = ""

        when:
        to YahooQueryPage

        and:
        search(q)

        then:
        waitFor {at YahooQueryPage}

    }
}

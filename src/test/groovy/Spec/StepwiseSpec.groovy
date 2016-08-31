package Spec

/**
 * Created by mehmetgerceker on 11/17/15.
 */
import Pages.BingResultPage
import Pages.YahooQueryPage
import Pages.YahooResultPage
import geb.driver.CachingDriverFactory
import spock.lang.*

@Stepwise
class StepwiseSpec extends SauceSpecBase {

    def setup() {
        //setup goes here
    }

    def cleanup() {
        // CachingDriverFactory.clearCache()
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
        String q = "hello!"
        YahooResultPage.searchWord = q

        when:
        to YahooQueryPage

        and:
        search(q)

        then:
        waitFor {at YahooResultPage}
    }

}

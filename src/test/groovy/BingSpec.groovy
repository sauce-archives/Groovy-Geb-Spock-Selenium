/**
 * Created by mehmetgerceker on 11/17/15.
 */

import geb.driver.CachingDriverFactory
import geb.spock.GebSpec

class BingSpec extends GebSpec {

    def setup() {
        //setup goes here
    }

    def cleanup() {
        CachingDriverFactory.clearCache()
        driver.quit()
    }

    def "Search \"hello!\""() {
        String q = "hello!"
        BingResultPage.searchWord = q

        when:
        to BingQueryPage

        and:
        search(q)

        then:
        waitFor {at BingResultPage}
    }
    def "Search blank"() {
        String q = ""
        BingResultPage.searchWord = ""

        when:
        to BingQueryPage

        and:
        search(q)

        then:
        waitFor {at BingQueryPage}
    }
}

import geb.spock.GebSpec

class LoginFailSpec extends GebSpec {

    def "can get to the current Book of Geb"() {
        given:
        go "http://www.saucedemo.com"

        when:
        $("[data-test='username']") << "locked_out_user"

        and:
        $("[data-test='password']") << "secret_sauce"

        and:
        $("[type='submit']").click()

        then:
        assert $(".error-button").size() == 1
    }
}

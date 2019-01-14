class LoginFailSpec extends BasePageGebSpec {

    def "Logs In Unsuccessfully"() {
        given:
        go "http://www.saucedemo.com"

        when:
        $("[data-test='username']") << "locked_out_user"
        $("[data-test='password']") << "secret_sauce"
        $("[type='submit']").click()

        then:
        $(".error-button").size() == 1
    }
}

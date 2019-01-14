class LoginPassSpec extends BasePageGebSpec {

    def "Logs In Successfully"() {
        given:
        go "http://www.saucedemo.com"

        when:
        $("[data-test='username']") << "standard_user"
        $("[data-test='password']") << "secret_sauce"
        $("[type='submit']").click()

        then:
        currentUrl == "https://www.saucedemo.com/inventory.html"
    }
}

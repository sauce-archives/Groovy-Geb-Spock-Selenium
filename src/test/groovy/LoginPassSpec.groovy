class LoginPassSpec extends BasePageGebSpec {

    def "can get to the current Book of Geb"() {
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

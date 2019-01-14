class AddOneToCartSpec extends BasePageGebSpec {
    def "Adds One to Cart"() {
        given:
        go "http://www.saucedemo.com/inventory.html"

        when:
        $(".add-to-cart-button", 0).click()

        then:
        $(".shopping_cart_badge").text() == '1'

        when:
        go "https://www.saucedemo.com/cart.html"

        then:
        $(".inventory_item_name").size() == 1
    }

    def "Adds Two to Cart"() {
        given:
        go "http://www.saucedemo.com/inventory.html"

        when:
        $(".add-to-cart-button", 0).click()
        $(".add-to-cart-button", 1).click()

        then:
        $(".shopping_cart_badge").text() == '2'

        when:
        go "https://www.saucedemo.com/cart.html"

        then:
        $(".inventory_item_name").size() == 2
    }
}

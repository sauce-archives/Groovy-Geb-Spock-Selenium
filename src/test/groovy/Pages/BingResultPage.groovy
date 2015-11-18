package Pages
/**
 * Created by mehmetgerceker on 11/17/15.
 */
class BingResultPage extends BingQueryPage {

    public static String searchWord;

    static at = { title.startsWith(searchWord) }
}
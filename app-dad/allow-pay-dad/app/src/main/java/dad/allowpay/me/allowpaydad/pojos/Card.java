package dad.allowpay.me.allowpaydad.pojos;

/**
 * Created by guilherme on 26/08/17.
 */

public class Card {

    Long id;
    String document;
    String nickname;

    public Card(String document, String nickname){
        this.document = document;
        this.nickname =nickname;
    }

}

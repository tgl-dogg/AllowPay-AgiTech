package dad.allowpay.me.allowpaydad.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Pitstop on 26/08/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance implements Serializable {

    private String cardId;
    private Integer value;

    public Balance() {
    }

    public Balance(String cardId, Integer value) {
        this.cardId = cardId;
        this.value = value;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

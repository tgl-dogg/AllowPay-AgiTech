package me.allowpay.allowpaydad.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Pitstop on 26/08/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Extract implements Serializable {

    private String cardId;
    private Integer value;
    private String date;
    private String merchant;
    private String type;

    public Extract() {
    }

    public Extract(String cardId, Integer value, String date, String merchant, String type) {
        this.cardId = cardId;
        this.value = value;
        this.date = date;
        this.merchant = merchant;
        this.type = type;
    }

    //    coordinate": {
//        "height": -22.8174189,
//                "width": -47.0408599
//    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

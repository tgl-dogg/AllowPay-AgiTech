package dad.allowpay.me.allowpaydad.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dad.allowpay.me.allowpaydad.R;
import dad.allowpay.me.allowpaydad.pojos.Card;

public class MenuActivity extends AppCompatActivity {

    private Button addCardButton;
    private Button rechargeCardButton;
    private List<Card> cards = new ArrayList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mockCard();
        buildAddCardButton();
        buildRechargeButton();

    }

    private void buildAddCardButton(){

        addCardButton = (Button) findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startAddCardActivity();
            }
        });

    }

    private void buildRechargeButton(){
        rechargeCardButton = (Button) findViewById(R.id.rechargeButton) ;
        rechargeCardButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startRechargeCardsActivity();
            }
        });
    }

    private List<Card> mockCard(){
        List<Card> cards = new ArrayList<Card>();
        Card card1 = new Card("01234567890", "Joãozinho");
        Card card2 = new Card("01234567890 ", "Pedrinho");

        cards.add(card1);
        cards.add(card2);

        return cards;
    }


    private void startCardDetailsActivity(){
        Intent cardDetailsIntent = new Intent(this, CardDetailsActivity.class);
        startActivity(cardDetailsIntent);
    }

    private void startRechargeCardsActivity(){
        Intent chargeCardsIntent = new Intent(this, ChargeCardsActivity.class);
        startActivity(chargeCardsIntent);
    }

    private void startAddCardActivity(){
        Intent addCardIntent = new Intent(this, AddCardActivity.class);
        startActivity(addCardIntent);
    }

}

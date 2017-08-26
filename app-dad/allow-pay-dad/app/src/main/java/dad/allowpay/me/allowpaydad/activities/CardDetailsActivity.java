package dad.allowpay.me.allowpaydad.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dad.allowpay.me.allowpaydad.R;

public class CardDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
    }

    private void startChargeCardsActivity(){
        Intent chargeCardsIntent = new Intent(this, ChargeCardsActivity.class);
        startActivity(chargeCardsIntent);
    }

}

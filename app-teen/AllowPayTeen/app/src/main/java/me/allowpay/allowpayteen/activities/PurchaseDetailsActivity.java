package me.allowpay.allowpayteen.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import me.allowpay.allowpayteen.R;
import me.allowpay.allowpayteen.pojos.Extract;
import me.allowpay.allowpayteen.utils.LocalBroadcastUtils;

public class PurchaseDetailsActivity extends AppCompatActivity {

    private Extract extract;

    private ImageView mImageViewMap;

    private TextView mTextViewMerchant, mTextViewValue, mTextViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);

        extract = (Extract) getIntent().getSerializableExtra(LocalBroadcastUtils.ACTION_REQUEST_AP_EXTRACT);
        instanceViews();
        setPurchaseDetails();
    }

    private void instanceViews() {
        mTextViewMerchant = (TextView) findViewById(R.id.textViewMerchant);
        mTextViewValue = (TextView) findViewById(R.id.textViewValue);
        mTextViewDate = (TextView) findViewById(R.id.textViewDate);

        mImageViewMap = (ImageView) findViewById(R.id.map);
        mImageViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=-23.5914,-46.6899");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    private void setPurchaseDetails() {
        String merchant = extract.getMerchant();
        String value = String.format(Locale.ROOT, "R$: %.2f", extract.getValue()/100f);
        String date = extract.getDate();
        mTextViewMerchant.setText(merchant);
        mTextViewValue.setText(value);
        mTextViewDate.setText(date);
    }
}

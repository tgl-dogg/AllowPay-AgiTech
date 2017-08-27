package dad.allowpay.me.allowpaydad.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.visa.checkout.VisaMcomLibrary;
import com.visa.checkout.VisaPaymentInfo;
import com.visa.checkout.utils.VisaEnvironmentConfig;
import com.visa.checkout.widget.VisaExpressCheckoutButton;

import dad.allowpay.me.allowpaydad.ConfigureVisaPaymentInfo;
import dad.allowpay.me.allowpaydad.R;

public class RechargeCardActivity extends AppCompatActivity implements VisaExpressCheckoutButton.CheckoutWithVisaListener {


    private VisaMcomLibrary visaMcomLibrary = null;
    private VisaPaymentInfo visaPaymentInfo = null;

    public Double pedidoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeVisaCheckoutSdk();

        VisaExpressCheckoutButton visaPaymentButton = (VisaExpressCheckoutButton) findViewById(R.id.visaEXOButton);
        setContentView(R.layout.activity_recharge_card);
    }

    //Visa Checkout

    private void initializeVisaCheckoutSdk() {
        VisaEnvironmentConfig visaEnvironmentConfig = ConfigureVisaPaymentInfo.VISA_CHECKOUT_ENVIRONMENT_CONFIG;

        /** Optional: a non-empty profile name obtained during enrollment can be included here. */
        //visaEnvironmentConfig.setMerchantProfileName(ConfigureVisaPaymentInfo.VISA_CHECKOUT_PROFILE_NAME);

        /** Required. Merchant API key obtained after enrollment. */
        visaEnvironmentConfig.setMerchantApiKey(ConfigureVisaPaymentInfo.VISA_CHECKOUT_API_KEY);

        /** Required for EXOButton integration only. Optional for VisaPaymentButton and CustomView integration.
         *  RequestCode to start SDK activity with, may be used in onActivityResult() as indicator that result came from the SDK. */
        visaEnvironmentConfig.setVisaCheckoutRequestCode(ConfigureVisaPaymentInfo.VISA_CHECKOUT_REQUEST_CODE);

        /** getLibrary should be invoked when the activity/application is created. getLibrary initializes the SDK
         in the background and makes the launch faster when user clicks the checkout button/view.  */
        visaMcomLibrary = VisaMcomLibrary.getLibrary(this, visaEnvironmentConfig);
    }

    public VisaMcomLibrary getVisaMcomLibrary() {
        return visaMcomLibrary;
    }

    public VisaPaymentInfo getVisaPaymentInfo() {
        if (visaPaymentInfo == null) {
            visaPaymentInfo = ConfigureVisaPaymentInfo.getSampleVisaPaymentInfo(pedidoTotal);
        }

        // pass through reference Call Id if present
        if (getIntent().getExtras() != null) {
            visaPaymentInfo.setReferenceCallId(getIntent().getExtras().getString(ConfigureVisaPaymentInfo.VISA_CHECKOUT_REFERENCE_ID_KEY));
        }

        return visaPaymentInfo;
    }

    public void checkoutWithVisa(View view) {
        getVisaMcomLibrary().checkoutWithPayment(getVisaPaymentInfo(), ConfigureVisaPaymentInfo.VISA_CHECKOUT_REQUEST_CODE);
    }

    private void startSummaryActivity(boolean success) {

        finish();
    }

    @Override
    public VisaPaymentInfo getPaymentInfo() {
        return getVisaPaymentInfo();
    }

}

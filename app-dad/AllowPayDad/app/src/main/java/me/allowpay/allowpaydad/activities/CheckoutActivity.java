package me.allowpay.allowpaydad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.visa.checkout.VisaLibrary;
import com.visa.checkout.VisaMcomLibrary;
import com.visa.checkout.VisaPaymentInfo;
import com.visa.checkout.VisaPaymentSummary;
import com.visa.checkout.utils.VisaEnvironmentConfig;
import com.visa.checkout.widget.VisaExpressCheckoutButton;

import me.allowpay.allowpaydad.R;
import me.allowpay.allowpaydad.visa.ConfigureVisaPaymentInfo;


import static me.allowpay.allowpaydad.visa.ConfigureVisaPaymentInfo.VISA_CHECKOUT_API_KEY;
import static me.allowpay.allowpaydad.visa.ConfigureVisaPaymentInfo.VISA_CHECKOUT_PROFILE_NAME;
import static me.allowpay.allowpaydad.visa.ConfigureVisaPaymentInfo.VISA_CHECKOUT_REFERENCE_ID_KEY;
import static me.allowpay.allowpaydad.visa.ConfigureVisaPaymentInfo.VISA_CHECKOUT_REQUEST_CODE;
import static me.allowpay.allowpaydad.visa.ConfigureVisaPaymentInfo.VISA_CHECKOUT_ENVIRONMENT_CONFIG;
import static me.allowpay.allowpaydad.visa.ConfigureVisaPaymentInfo.VISA_CHECKOUT_RESULT_MSG_KEY;

public class CheckoutActivity extends AppCompatActivity implements VisaExpressCheckoutButton.CheckoutWithVisaListener {

    private static final String TAG = CheckoutActivity.class.getSimpleName();

    private VisaMcomLibrary visaMcomLibrary = null;
    private VisaPaymentInfo visaPaymentInfo = null;
    private int buttonType;
    private ImageView customView;
    private VisaEnvironmentConfig visaEnvironmentConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeVisaCheckoutSdk();

        setContentView(R.layout.activity_checkout);
    }

    private void initializeVisaCheckoutSdk() {
        VisaEnvironmentConfig visaEnvironmentConfig = VISA_CHECKOUT_ENVIRONMENT_CONFIG;

        /** Optional: a non-empty profile name obtained during enrollment can be included here. */
        visaEnvironmentConfig.setMerchantProfileName(VISA_CHECKOUT_PROFILE_NAME);

        /** Required. Merchant API key obtained after enrollment. */
        visaEnvironmentConfig.setMerchantApiKey(VISA_CHECKOUT_API_KEY);

        /** Required for EXOButton integration only. Optional for VisaPaymentButton and CustomView integration.
         *  RequestCode to start SDK activity with, may be used in onActivityResult() as indicator that result came from the SDK. */
        visaEnvironmentConfig.setVisaCheckoutRequestCode(VISA_CHECKOUT_REQUEST_CODE);

        /** getLibrary should be invoked when the activity/application is created. getLibrary initializes the SDK
         in the background and makes the launch faster when user clicks the checkout button/view.  */
        visaMcomLibrary = VisaMcomLibrary.getLibrary(this, visaEnvironmentConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VISA_CHECKOUT_REQUEST_CODE) {
            Log.d(TAG, "Result got back from Visa Checkout SDK");
            String msg = null;
            Bundle bundle = new Bundle();

            if (resultCode == VisaLibrary.RESULT_OK && data != null) {
                VisaPaymentSummary paymentSummary = data.getParcelableExtra(VisaLibrary.PAYMENT_SUMMARY);
                if (paymentSummary != null) {
                    msg = "Purchase Success!";
                    bundle.putParcelable(VisaLibrary.PAYMENT_SUMMARY, paymentSummary);

                    // we are done with this activity
                    finish();

                    // start the next activity
//                    Intent intent = new Intent(this, PaymentSummaryActivity.class);
//                    bundle.putString(VISA_CHECKOUT_RESULT_MSG_KEY, msg);
//                    intent.putExtra("buttonType", buttonType);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }
            } else if (resultCode == VisaLibrary.ERROR_VALIDATION_FAILED) {
                msg = "VisaPaymentInfo validation failed, Result Code : " + resultCode;
            } else if (resultCode == VisaLibrary.SDK_VERSION_NOT_SUPPORTED) {
                msg = "SDK Version is not Supported, Result Code : " + requestCode;
            } else if (resultCode == VisaLibrary.OS_VERSION_NOT_SUPPORTED) {
                msg = "Device OS version is not supported, Result Code : " + resultCode;
            } else if (resultCode == VisaLibrary.VISA_VIEW_INIT_FAILED) {
                msg = "Initializing Visa Checkout SDK failed, Result Code : " + resultCode;
            } else {
                msg = "Purchase failed!";
            }

            Log.d(TAG, msg);

            // show the failure reason
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public VisaMcomLibrary getVisaMcomLibrary() {
        return visaMcomLibrary;
    }

    public VisaPaymentInfo getVisaPaymentInfo() {
        if (visaPaymentInfo == null) {
            visaPaymentInfo = ConfigureVisaPaymentInfo.getSampleVisaPaymentInfo();
        }

        // pass through reference Call Id if present
        if (getIntent().getExtras() != null) {
            visaPaymentInfo.setReferenceCallId(
                    getIntent().getExtras().getString(VISA_CHECKOUT_REFERENCE_ID_KEY));
        }

        return visaPaymentInfo;
    }

    public void checkoutWithVisa(View view) {
        getVisaMcomLibrary().checkoutWithPayment(
                getVisaPaymentInfo(),
                VISA_CHECKOUT_REQUEST_CODE);
    }

    @Override
    public VisaPaymentInfo getPaymentInfo() {
        return getVisaPaymentInfo();
    }

}

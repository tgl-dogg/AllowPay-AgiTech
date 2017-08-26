package me.allowpay.allowpayteen.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import me.allowpay.allowpayteen.activities.ExtractActivity;
import me.allowpay.allowpayteen.pojos.Bonus;
import me.allowpay.allowpayteen.utils.HttpUtils;
import me.allowpay.allowpayteen.utils.LocalBroadcastUtils;

/**
 * Created by Pitstop on 26/08/2017.
 */
public class BonusAsyncTask extends AsyncTask<String, Void, Bonus> {

    private static final String endpoint = HttpUtils.URL + "bonus/";

    private Context mContext;

    private ProgressDialog mProgressDialog;

    public BonusAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Carregando...");
        mProgressDialog.show();
    }

    @Override
    protected Bonus doInBackground(String... params) {
        String url = endpoint + params[0];
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(HttpUtils.getDefaultHttpHeaders());

        ResponseEntity<Bonus> response = restTemplate.exchange(url, HttpMethod.GET, entity, Bonus.class);

        Bonus bonus = response.getBody();
        return bonus;
    }

    @Override
    protected void onPostExecute(Bonus bonus) {
        Intent intent = new Intent(mContext, ExtractActivity.class);
        intent.putExtra(LocalBroadcastUtils.ACTION_REQUEST_AP_BONUS, bonus);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
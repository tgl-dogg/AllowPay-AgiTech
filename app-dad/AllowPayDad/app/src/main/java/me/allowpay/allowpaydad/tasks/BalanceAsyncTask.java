package me.allowpay.allowpaydad.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import me.allowpay.allowpaydad.activities.ExtractActivity;
import me.allowpay.allowpaydad.pojos.Balance;
import me.allowpay.allowpaydad.utils.HttpUtils;
import me.allowpay.allowpaydad.utils.LocalBroadcastUtils;

/**
 * Created by Pitstop on 26/08/2017.
 */
public class BalanceAsyncTask extends AsyncTask<String, Void, Balance> {

    private static final String endpoint = "/balance";

    private Context mContext;

    private ProgressDialog mProgressDialog;

    public BalanceAsyncTask(Context context) {
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
    protected Balance doInBackground(String... params) {
        String url = HttpUtils.URL + "cards/" + params[0] + endpoint;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity entity = new HttpEntity(HttpUtils.getDefaultHttpHeaders());

        ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.GET, entity, Balance.class);

        Balance balance = response.getBody();
        return balance;
    }

    @Override
    protected void onPostExecute(Balance balance) {
        Intent intent = new Intent(LocalBroadcastUtils.ACTION_REQUEST_AP_BALANCE);
        intent.putExtra(LocalBroadcastUtils.ACTION_REQUEST_AP_BALANCE, balance);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

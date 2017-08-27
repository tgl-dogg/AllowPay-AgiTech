package me.allowpay.allowpayteen.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import me.allowpay.allowpayteen.activities.ExtractActivity;
import me.allowpay.allowpayteen.utils.HttpUtils;

/**
 * Created by Pitstop on 26/08/2017.
 */
public class CardLockAsyncTask extends AsyncTask<String, Void, String> {

    private static final String endpoint = HttpUtils.URL + "block/";

    private Context mContext;

    private ProgressDialog mProgressDialog;

    public CardLockAsyncTask(Context context) {
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
    protected String doInBackground(String... params) {
        String url = endpoint + params[0];
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(HttpUtils.getDefaultHttpHeaders());

        String response = restTemplate.postForObject(url, entity, String.class);
        return response;
    }

    @Override
    protected void onPostExecute(String param) {
        Intent intent = new Intent(mContext, ExtractActivity.class);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

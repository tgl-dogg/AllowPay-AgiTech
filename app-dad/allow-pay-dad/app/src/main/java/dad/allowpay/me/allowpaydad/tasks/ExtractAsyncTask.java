package dad.allowpay.me.allowpaydad.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import dad.allowpay.me.allowpaydad.pojos.Extract;
import dad.allowpay.me.allowpaydad.utils.HttpUtils;
import dad.allowpay.me.allowpaydad.utils.LocalBroadcastUtils;

/**
 * Created by Pitstop on 26/08/2017.
 */
public class ExtractAsyncTask extends AsyncTask<String, Void, ArrayList<Extract>> {

    private static final String endpoint = "/extract";

    private Context mContext;

    private ProgressDialog mProgressDialog;

    public ExtractAsyncTask(Context context) {
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
    protected ArrayList<Extract> doInBackground(String... params) {
        String url = HttpUtils.URL + "cards/" + params[0] + endpoint;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity entity = new HttpEntity(HttpUtils.getDefaultHttpHeaders());

        ResponseEntity<ArrayList<Extract>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<ArrayList<Extract>>() {
                    // vários nadas.
                });

        ArrayList<Extract> extracts = response.getBody();
        return extracts;
    }

    @Override
    protected void onPostExecute(ArrayList<Extract> extracts) {
        Intent intent = new Intent(LocalBroadcastUtils.ACTION_REQUEST_AP_EXTRACT);
        intent.putExtra(LocalBroadcastUtils.ACTION_REQUEST_AP_EXTRACT, extracts);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

package dad.allowpay.me.allowpaydad.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dad.allowpay.me.allowpaydad.R;
import dad.allowpay.me.allowpaydad.pojos.Balance;
import dad.allowpay.me.allowpaydad.pojos.Bonus;
import dad.allowpay.me.allowpaydad.pojos.Extract;
import dad.allowpay.me.allowpaydad.tasks.BalanceAsyncTask;
import dad.allowpay.me.allowpaydad.tasks.ExtractAsyncTask;
import dad.allowpay.me.allowpaydad.utils.LocalBroadcastUtils;

public class ExtractActivity extends AppCompatActivity {

    private ListView mListView;
    private TextView mTextViewBalance, mTextViewBonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract);

        instanceViews();
        registerBroadcasts();
        startRequests();
    }

    @Override
    public void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private void instanceViews() {
        mTextViewBonus = (TextView) findViewById(R.id.textViewBonus);
        mTextViewBalance = (TextView) findViewById(R.id.textViewBalance);

        mListView = (ListView) findViewById(R.id.listView);
    }

    private void registerBroadcasts() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

        IntentFilter intentFilter = new IntentFilter(LocalBroadcastUtils.ACTION_REQUEST_AP_BALANCE);
        localBroadcastManager.registerReceiver(mMessageReceiver, intentFilter);

        intentFilter = new IntentFilter(LocalBroadcastUtils.ACTION_REQUEST_AP_BONUS);
        localBroadcastManager.registerReceiver(mMessageReceiver, intentFilter);

        intentFilter = new IntentFilter(LocalBroadcastUtils.ACTION_REQUEST_AP_EXTRACT);
        localBroadcastManager.registerReceiver(mMessageReceiver, intentFilter);
    }

    private void startRequests() {
        new BalanceAsyncTask(this).execute("000001");
        new dad.allowpay.me.allowpaydad.tasks.BonusAsyncTask(this).execute("000001");
        new ExtractAsyncTask(this).execute("000001");
    }

    private void updateBalance(Balance balance) {
        String balanceFormatted = String.format(Locale.ROOT, "R$: %.2f", balance.getValue() / 100f);
        mTextViewBalance.setText(balanceFormatted.replace('.', ','));
    }

    private void updateBonus(Bonus bonus) {
        mTextViewBonus.setText(bonus.getValue().toString());
    }

    private void updateExtract(ArrayList<Extract> extracts) {
        List<String> array = new ArrayList<>();
        for (Extract extract : extracts) {
            String s = String.format(Locale.ROOT, "%s - R$: %.2f", extract.getMerchant(), extract.getValue() / 100f);
            array.add(s);
        }
//        array.add("alêEnígena");
//        array.add("SAbreu");
//        array.add("sCobar");
//        array.add("rCabrals");
//        array.add("tglDogg");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array);

        mListView.setAdapter(arrayAdapter);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("action", action);

            switch (action) {
                case LocalBroadcastUtils.ACTION_REQUEST_AP_BALANCE:
                    Balance balance = (Balance) intent.getSerializableExtra(action);
                    updateBalance(balance);
                    break;

                case LocalBroadcastUtils.ACTION_REQUEST_AP_BONUS:
                    Bonus bonus = (Bonus) intent.getSerializableExtra(action);
                    updateBonus(bonus);
                    break;

                case LocalBroadcastUtils.ACTION_REQUEST_AP_EXTRACT:
                    ArrayList<Extract> extracts = (ArrayList<Extract>) intent.getSerializableExtra(action);
                    updateExtract(extracts);
                    break;
            }
        }
    };
}

package me.allowpay.allowpayteen.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.allowpay.allowpayteen.R;
import me.allowpay.allowpayteen.pojos.Balance;
import me.allowpay.allowpayteen.pojos.Bonus;
import me.allowpay.allowpayteen.pojos.Extract;
import me.allowpay.allowpayteen.tasks.BalanceAsyncTask;
import me.allowpay.allowpayteen.tasks.BonusAsyncTask;
import me.allowpay.allowpayteen.tasks.CardLockAsyncTask;
import me.allowpay.allowpayteen.tasks.ExtractAsyncTask;
import me.allowpay.allowpayteen.utils.LocalBroadcastUtils;

public class ExtractActivity extends AppCompatActivity {

    private static final String PROXY = "3713100015978";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.extract_menu, menu);
        return true;
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
        new BalanceAsyncTask(this).execute(PROXY);
        new BonusAsyncTask(this).execute(PROXY);
        new ExtractAsyncTask(this).execute(PROXY);
    }

    private void updateBalance(Balance balance) {
        String balanceFormatted = String.format(Locale.ROOT, "R$: %.2f", balance.getValue() / 100f);
        mTextViewBalance.setText(balanceFormatted.replace('.', ','));
    }

    private void blockCard() {
        // new CardLockAsyncTask(this).execute(PROXY);
        Toast.makeText(this, "Em implementação...", Toast.LENGTH_LONG).show();
    }

    private void updateBonus(Bonus bonus) {
        mTextViewBonus.setText(bonus.getValue().toString());
    }

    private void updateExtract(final ArrayList<Extract> extracts) {
        List<String> array = new ArrayList<>();
        for (Extract extract : extracts) {
            String s = String.format(Locale.ROOT, "%s - R$: %.2f", extract.getMerchant(), extract.getValue() / 100f);
            array.add(s);
        }

        //array.add("alêEnígena");
        //array.add("SAbreu");
        //array.add("sCobar");
        //array.add("rCabrals");
        //array.add("tglDogg");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array);

        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ExtractActivity.this, PurchaseDetailsActivity.class);
                i.putExtra(LocalBroadcastUtils.ACTION_REQUEST_AP_EXTRACT, extracts.get(position));
                startActivity(i);
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                startRequests();
                return true;
            case R.id.action_lock_card:
                blockCard();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

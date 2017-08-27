package dad.allowpay.me.allowpaydad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dad.allowpay.me.allowpaydad.activities.MenuActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MenuActivity.class);

        startActivity(intent);

    }
}

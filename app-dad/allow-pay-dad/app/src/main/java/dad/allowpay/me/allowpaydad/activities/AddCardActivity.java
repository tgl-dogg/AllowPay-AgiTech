package dad.allowpay.me.allowpaydad.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import dad.allowpay.me.allowpaydad.R;

public class AddCardActivity extends AppCompatActivity {

    private Button mButtonRegisterCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instaceButtons();
        setContentView(R.layout.activity_add_card);
    }

    private void instaceButtons(){
//        buildRegisterCardButton();
    }

    private void buildRegisterCardButton(){

        mButtonRegisterCard = (Button) findViewById(R.id.buttonRegisterCard);
        mButtonRegisterCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startMenu();
            }
        });

    }


    private void startMenu(){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }

}

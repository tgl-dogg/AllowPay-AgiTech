package dad.allowpay.me.allowpaydad.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import dad.allowpay.me.allowpaydad.R;

public class AddCardActivity extends AppCompatActivity {

    private ImageView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
    }


//    protected void cancelCardRegister(){
//        cancelButton = (ImageView) findViewById(R.id.cancelButton);
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder =
//                        new AlertDialog.Builder(AddCardActivity.this, R.style.AppCompatAlertDialogStyle);
//                builder.setTitle("Alerta");
//                builder.setMessage("Tem certeza que deseja cancelar o cadastro?");
//                builder.setPositiveButton("Não", null);
//                builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent myIntent = new Intent(this, MenuActivity.class);
//                        startActivity(myIntent);
//                        finish();
//                    }
//                });
//
//                builder.show();
//            }
//        });
//    }

}

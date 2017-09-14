package ir.dr_hesaabi.infa;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ShowSignUpDialog();
            }
        },500);
    }
    private void ShowSignUpDialog(){
        final Dialog dialog = new Dialog(ActivityLogin.this,R.style.LoginDialogAnimation);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.dialog_sms_sign_up);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.LoginDialogAnimation;
        CardView cardView = (CardView) dialog.findViewById(R.id.cardView);
        cardView.setVisibility(View.VISIBLE);
        EditText edtPhoneNumber = (EditText) dialog.findViewById(R.id.edtPhoneNumber);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dialog.show();
    }
}

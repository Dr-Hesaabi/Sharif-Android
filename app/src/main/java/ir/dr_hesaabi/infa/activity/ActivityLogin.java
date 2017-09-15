package ir.dr_hesaabi.infa.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.qrCode.zXing.FullScannerActivity;
import ir.dr_hesaabi.infa.utils.Font;
import ir.dr_hesaabi.infa.webService.VolleyAdapter;

public class ActivityLogin extends AppCompatActivity {

    private ImageView imgBg;
    public static SharedPreferences shared;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onResume() {
        super.onResume();
        if (shared.getBoolean("firstrun", true)) {
            editor.putString("mobile","");
            editor.putString("name","");
            editor.putString("family","");
            editor.commit();
            editor.putBoolean("firstrun", false).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitViews();

        shared = getSharedPreferences("ir.dr_hesaabi.infa", MODE_PRIVATE);
        editor = shared.edit();

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
        final EditText edtPhoneNumber = (EditText) dialog.findViewById(R.id.edtPhoneNumber);
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
                String phoneNumber = edtPhoneNumber.getText().toString();
                if (phoneNumber.length() == 0) {
                    edtPhoneNumber.setError("لطفا شماره تلفن خود را وارد کنید!");
                    return;
                } else if (phoneNumber.length() < 11) {
                    edtPhoneNumber.setError("شماره وارد شده صحیح نیست!");
                    return;
                } else {
//                    VolleyAdapter volleyAdapter = new VolleyAdapter(context);
//                    volleyAdapter.PostPhoneNumberAsyncTask.execute("phoneNumber");
                }
            }
        });
        btnOk.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShowSigninDialog();
                    }
                },700);
                return false;
            }
        });
        dialog.show();
    }

    private void ShowSigninDialog(){
        final Dialog dialog = new Dialog(ActivityLogin.this,R.style.LoginDialogAnimation);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.dialog_sms_sign_in);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.LoginDialogAnimation;
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
        btnOk.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ActivityLogin.this.startActivity(new Intent(ActivityLogin.this,ActivityHome.class));
                        finish();
                    }
                },700);
                return false;
            }
        });
        dialog.show();
    }

    private void InitViews(){
        Font.setFont((ViewGroup) getWindow().getDecorView(), ActivityLogin.this, "behdad.ttf");

        imgBg = (ImageView) findViewById(R.id.imgBg);

//        final Random random = new Random();
//        final Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                //call function
//                ScaleAnimation scaleAnimation = new ScaleAnimation((random.nextInt(10)%2)+1,(random.nextInt(10)%2)+1,(random.nextInt(10)%2)+1,(random.nextInt(10)%2)+1,imgBg.getPivotX(),imgBg.getPivotY());
//                scaleAnimation.setDuration(5000);
//                imgBg.startAnimation(scaleAnimation);
//                handler.postDelayed(this,5000);
//            }
//        }, 5000);
    }
}

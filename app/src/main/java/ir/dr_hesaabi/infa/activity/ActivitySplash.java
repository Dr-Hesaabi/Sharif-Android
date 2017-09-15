package ir.dr_hesaabi.infa.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.qrCode.zXing.FullScannerActivity;
import pl.droidsonroids.gif.GifImageView;

public class ActivitySplash extends AppCompatActivity {

    GifImageView gifSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        InitViews();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivitySplash.this.startActivity(new Intent(ActivitySplash.this,ActivityLogin.class));
            }
        },2500);

    }
    private void InitViews(){
        gifSplash = (GifImageView) findViewById(R.id.gifSplash);

        //get screen screenWidth and set pager screenWidth and height
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int Width = displaymetrics.widthPixels/2;
        gifSplash.setLayoutParams(new LinearLayout.LayoutParams(Width, (int) (Width*0.727)));

    }
}

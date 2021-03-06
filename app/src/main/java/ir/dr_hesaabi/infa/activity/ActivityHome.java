package ir.dr_hesaabi.infa.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import java.util.ArrayList;
import java.util.List;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.adapter.OrderFeaturesLVAdapter;
import ir.dr_hesaabi.infa.modol.OrderFeature;
import ir.dr_hesaabi.infa.qrCode.zXing.FullScannerActivity;
import ir.dr_hesaabi.infa.utils.Font;

public class ActivityHome extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialMenuView materialButtonRight;
    private DrawerLayout drawerLayout;
    private ImageView imgSearch;
    private FloatingActionButton fabBarcodeScanner;
    private LinearLayout llAccount,llIncreaseInventory,llTransaction,llExit;
    private ListView lvOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        FillUpListview();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this,ActivitySearch.class));
            }
        });

        fabBarcodeScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this,FullScannerActivity.class));
            }
        });

        findViewById(R.id.llAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this,ActivityEditProfile.class));
            }
        });
        findViewById(R.id.llIncreaseInventory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this,ActivityIncreaseInventory.class));
            }
        });
        findViewById(R.id.llTransaction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this,ActivityBandSuccessTransaction.class));
            }
        });
        findViewById(R.id.llExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        materialButtonRight.setRotation(180);
        materialButtonRight.setTransformationDuration(1500);
        materialButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    materialButtonRight.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                }else{
                    drawerLayout.openDrawer(Gravity.RIGHT);
                    materialButtonRight.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                }

            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Called when a drawer's position changes.
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                materialButtonRight.animateIconState(MaterialMenuDrawable.IconState.ARROW);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                materialButtonRight.animateIconState(MaterialMenuDrawable.IconState.BURGER);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
            }
        });

    }

    private void FillUpListview(){
        // rexa
        List<OrderFeature> productFeatures = new ArrayList<>();
        OrderFeature orderFeature = new OrderFeature();
        orderFeature.setName("کفش");
        orderFeature.setStore("فروشاه نیما");
        orderFeature.setCost("25000 تومان");
        orderFeature.setImage("");
        productFeatures.add(orderFeature);
        productFeatures.add(orderFeature);
        productFeatures.add(orderFeature);
        productFeatures.add(orderFeature);
        productFeatures.add(orderFeature);
        final OrderFeaturesLVAdapter orderFeaturesLVAdapter = new OrderFeaturesLVAdapter(ActivityHome.this,productFeatures);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lvOrders.setAdapter(orderFeaturesLVAdapter);
            }
        },500);
    }

    private void initViews(){
        Font.setFont((ViewGroup) getWindow().getDecorView(), ActivityHome.this, "behdad.ttf");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialButtonRight = (MaterialMenuView) findViewById(R.id.material_menu_button_right);
        drawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));
        imgSearch = ((ImageView) findViewById(R.id.imgSearch));
        fabBarcodeScanner = (FloatingActionButton) findViewById(R.id.fabBarcodeScanner);
        llAccount = (LinearLayout) findViewById(R.id.llAccount);
        llIncreaseInventory = (LinearLayout) findViewById(R.id.llIncreaseInventory);
        llTransaction = (LinearLayout) findViewById(R.id.llTransaction);
        llExit = (LinearLayout) findViewById(R.id.llExit);
        lvOrders = (ListView) findViewById(R.id.lvOrders);
    }
}

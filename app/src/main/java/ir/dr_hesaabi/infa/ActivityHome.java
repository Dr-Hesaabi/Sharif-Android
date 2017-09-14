package ir.dr_hesaabi.infa;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

public class ActivityHome extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialMenuView materialButtonRight;
    private DrawerLayout drawerLayout;
    private ImageView imgSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this,ActivitySearch.class));
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

    private void initViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialButtonRight = (MaterialMenuView) findViewById(R.id.material_menu_button_right);
        drawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));
        imgSearch = ((ImageView) findViewById(R.id.imgSearch));
    }
}

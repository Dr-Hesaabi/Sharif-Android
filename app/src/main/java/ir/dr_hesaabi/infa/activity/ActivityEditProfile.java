package ir.dr_hesaabi.infa.activity;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.utils.Font;

public class ActivityEditProfile extends AppCompatActivity {

    AppBarLayout appbar;
    Toolbar toolbar;
    ImageButton imgProfilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initViews();
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    imgProfilePic.setVisibility(View.GONE);
                } else {
                    // Somewhere in between
                    imgProfilePic.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void initViews(){
        Font.setFont((ViewGroup) getWindow().getDecorView(), ActivityEditProfile.this, "behdad.ttf");

        appbar = (AppBarLayout) findViewById(R.id.main_appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgProfilePic = (ImageButton) findViewById(R.id.imgProfilePic);
    }
}

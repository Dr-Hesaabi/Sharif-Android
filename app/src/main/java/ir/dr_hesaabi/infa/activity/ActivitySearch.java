package ir.dr_hesaabi.infa.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.utils.Font;

public class ActivitySearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        InitViews();
    }

    private void InitViews(){
        Font.setFont((ViewGroup) getWindow().getDecorView(), ActivitySearch.this, "behdad.ttf");

    }
}

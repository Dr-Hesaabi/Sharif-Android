package ir.dr_hesaabi.infa.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.adapter.OrderFeaturesLVAdapter;
import ir.dr_hesaabi.infa.adapter.SearchProductFeaturesLVAdapter;
import ir.dr_hesaabi.infa.modol.OrderFeature;
import ir.dr_hesaabi.infa.modol.SearchProductFeature;
import ir.dr_hesaabi.infa.utils.Font;

public class ActivitySearch extends AppCompatActivity {

    private ListView lvSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        InitViews();
        FillUpListview();
    }

    private void InitViews(){
        Font.setFont((ViewGroup) getWindow().getDecorView(), ActivitySearch.this, "behdad.ttf");

        lvSearch = (ListView) findViewById(R.id.lvSearch);
    }

    private void FillUpListview(){
        // rexa
        List<SearchProductFeature> searchProductFeatures = new ArrayList<>();
        SearchProductFeature searchProduct = new SearchProductFeature();
        searchProduct.setName("کفش");
        searchProduct.setNewCost("175000 تومان");
        searchProduct.setOldCost("200000 تومان");
        searchProduct.setOff("12.5%");
        searchProduct.setImage("");
        searchProductFeatures.add(searchProduct);
        searchProductFeatures.add(searchProduct);
        searchProductFeatures.add(searchProduct);
        searchProductFeatures.add(searchProduct);
        searchProductFeatures.add(searchProduct);
        final SearchProductFeaturesLVAdapter searchProductFeaturesLVAdapter = new SearchProductFeaturesLVAdapter(ActivitySearch.this,searchProductFeatures);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lvSearch.setAdapter(searchProductFeaturesLVAdapter);
            }
        },500);
    }

}

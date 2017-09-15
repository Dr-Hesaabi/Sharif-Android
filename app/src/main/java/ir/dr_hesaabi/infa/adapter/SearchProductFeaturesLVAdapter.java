package ir.dr_hesaabi.infa.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.modol.SearchProductFeature;

public class SearchProductFeaturesLVAdapter extends BaseAdapter {

    private static Context context;
    private static List<SearchProductFeature> searchProductFeatures;
    private static LayoutInflater layoutInflater = null;

    private static TextView txtProductName, txtNewCost,txtOldCost,txtOff;

    public SearchProductFeaturesLVAdapter(Activity activity, List<SearchProductFeature> searchProductFeatures) {
        this.context = activity;
        this.searchProductFeatures = searchProductFeatures;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final View rowView = layoutInflater.inflate(R.layout.item_lv_search, null);
        txtProductName = (TextView) rowView.findViewById(R.id.txtProductName);
        txtNewCost = (TextView) rowView.findViewById(R.id.txtNewCost);
        txtOldCost = (TextView) rowView.findViewById(R.id.txtOldCost);
        txtOff = (TextView) rowView.findViewById(R.id.txtOff);
        txtProductName.setText(searchProductFeatures.get(position).getName()+"");
        txtNewCost.setText(searchProductFeatures.get(position).getNewCost()+"");
        txtOldCost.setText(searchProductFeatures.get(position).getOldCost()+"");
        txtOff.setText(searchProductFeatures.get(position).getOff()+"");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1,rowView.getPivotX(),rowView.getPivotY());
                scaleAnimation.setDuration(450);
                scaleAnimation.setFillAfter(true);
                rowView.startAnimation(scaleAnimation);
            }
        },50);
        return rowView;
    }

    @Override
    public int getCount() {
        return searchProductFeatures.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

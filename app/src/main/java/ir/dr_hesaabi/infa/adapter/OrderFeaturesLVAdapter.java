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
import ir.dr_hesaabi.infa.modol.OrderFeature;
import ir.dr_hesaabi.infa.modol.SearchProductFeature;

public class OrderFeaturesLVAdapter extends BaseAdapter {

    private static Context context;
    private static List<OrderFeature> orderFeatures;
    private static LayoutInflater layoutInflater = null;

    private static TextView txtName,txtStore,txtCost;

    public OrderFeaturesLVAdapter(Activity activity, List<OrderFeature> productFeatures) {
        this.context = activity;
        this.orderFeatures = productFeatures;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final View rowView = layoutInflater.inflate(R.layout.item_lv_orders, null);
        txtName = (TextView) rowView.findViewById(R.id.txtName);
        txtStore = (TextView) rowView.findViewById(R.id.txtStore);
        txtCost = (TextView) rowView.findViewById(R.id.txtCost);
        txtName.setText(orderFeatures.get(position).getName()+"");
        txtStore.setText(orderFeatures.get(position).getStore()+"");
        txtCost.setText(orderFeatures.get(position).getCost()+"");

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
        return orderFeatures.size();
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

//package ir.dr_hesaabi.infa;
//
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Bundle;
//import android.util.SparseArray;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.google.android.gms.vision.Frame;
//import com.google.android.gms.vision.barcode.Barcode;
//import com.google.android.gms.vision.barcode.BarcodeDetector;
//public class ActivityMain extends AppCompatActivity{
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
//        final Bitmap myBitmap = BitmapFactory.decodeResource(
//                getApplicationContext().getResources(),
//                R.drawable.puppy4);
//        myImageView.setImageBitmap(myBitmap);
//
//        final TextView txtView = (TextView) findViewById(R.id.txtContent);
//
//        final BarcodeDetector detector =
//                new BarcodeDetector.Builder(getApplicationContext())
//                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
//                        .build();
//        if(!detector.isOperational()){
//            txtView.setText("Could not set up the detector!");
//            return;
//        }
//
//
//
//        Button btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
//                SparseArray<Barcode> barcodes = detector.detect(frame);
//
//                Barcode thisCode = barcodes.valueAt(0);
//                txtView.setText(thisCode.rawValue);
//            }
//        });
//
//
//    }
//}
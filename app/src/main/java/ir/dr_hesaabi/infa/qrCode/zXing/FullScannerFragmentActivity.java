package ir.dr_hesaabi.infa.qrCode.zXing;

import android.os.Bundle;

import ir.dr_hesaabi.infa.R;

public class FullScannerFragmentActivity extends BaseScannerActivity {
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_full_scanner_fragment);
        setupToolbar();
    }
}
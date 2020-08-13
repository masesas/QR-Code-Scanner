package com.rkrzmail.card;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ViewActivity extends AppCompatActivity {

    private ImageView imageView;
    private Intent i;
    private TextView tvTittle;
    private Button btnScan;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.item_barcode);
        initComponent();
    }

    private void initComponent() {
        imageView = findViewById(R.id.img_barcode);
        tvTittle = findViewById(R.id.tv_tittle);
        btnScan = findViewById(R.id.btn_scan);
        cardView = findViewById(R.id.ly_result);
        cardView.setVisibility(View.GONE);

        i = getIntent();
        if (i.hasExtra("barcode")) {
            btnScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scanBarcode(new FrameLayout(ViewActivity.this));
                }
            });
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_qr_code));
        } else if (i.hasExtra("card")) {
            btnScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scanBarcode(new FrameLayout(ViewActivity.this));
                }
            });
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_qr_code));
        }
    }

    void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    void scanBarcode(View view) {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            cardView.setVisibility(View.VISIBLE);
            tvTittle.setText(result.getContents());
            if (result.getContents() == null) {
                toast("Failed");
            } else {
                toast("Scanned " + result.getContents());
            }
        } else {
            tvTittle.setText("NULL");
            toast("Failed");
        }
    }
}

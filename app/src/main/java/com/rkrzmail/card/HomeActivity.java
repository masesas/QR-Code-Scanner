package com.rkrzmail.card;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_BARCODE = 10;
    private static final int REQUEST_CARD = 11;
    private CardView lyBarcode, lyCard;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Menu");
    }

    private void initComponent(){
        lyBarcode = findViewById(R.id.ly_barcode);
        lyCard = findViewById(R.id.ly_card_name);

        lyBarcode.setOnClickListener(this);
        lyCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ly_barcode:
                i = new Intent(this, ViewActivity.class);
                i.putExtra("barcode", "");
                startActivityForResult(i, REQUEST_BARCODE);
                break;
            case R.id.ly_card_name:
                i = new Intent(this, ViewActivity.class);
                i.putExtra("card", "");
                startActivityForResult(i, REQUEST_CARD);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_BARCODE){

        }else if(resultCode == RESULT_OK && requestCode == REQUEST_CARD){

        }
    }
}

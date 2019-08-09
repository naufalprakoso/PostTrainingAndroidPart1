package com.naufalprakoso.posttrainingandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button btnCall = findViewById(R.id.btn_call);
        final Button btnWebsite = findViewById(R.id.btn_website);

        btnCall.setOnClickListener(this);
        btnWebsite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                String phoneNumber = "9999999999";
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));

                startActivity(phoneIntent);
                break;
            case R.id.btn_website:
                String url = "https://facebook.com";
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(url));

                startActivity(urlIntent);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}

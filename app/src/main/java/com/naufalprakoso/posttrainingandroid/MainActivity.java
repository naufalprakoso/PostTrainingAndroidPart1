package com.naufalprakoso.posttrainingandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIntent = findViewById(R.id.btn_intent);
        Button btnBasicComponent = findViewById(R.id.btn_basic_components);
        Button btnShared = findViewById(R.id.btn_shared);

        btnIntent.setOnClickListener(this);
        btnBasicComponent.setOnClickListener(this);
        btnShared.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_intent:
                intent(IntentActivity.class);
                break;
            case R.id.btn_basic_components:
                intent(RegisterActivity.class);
                break;
            case R.id.btn_shared:
                intent(SharedActivity.class);
                break;
        }
    }

    private void intent(Class myClass){
        Intent intent = new Intent(this, myClass);
        startActivity(intent);
    }
}

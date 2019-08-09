package com.naufalprakoso.posttrainingandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SharedActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername;
    private TextView txtUsername;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private LinearLayout layoutLogin, layoutLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        txtUsername = findViewById(R.id.txt_username);
        edtUsername = findViewById(R.id.edt_username);
        final Button btnLogin = findViewById(R.id.btn_login);
        final Button btnLogout = findViewById(R.id.btn_logout);

        layoutLogin = findViewById(R.id.layout_login);
        layoutLogged = findViewById(R.id.layout_logged);

        sharedPreferences = getSharedPreferences("TrainingShared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        checkLogin();

        btnLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    private void checkLogin(){
        final boolean isUsernameExists = sharedPreferences.contains("username");
        if (isUsernameExists){
            layoutLogin.setVisibility(View.GONE);
            layoutLogged.setVisibility(View.VISIBLE);

            final String username = sharedPreferences.getString("username", null);
            txtUsername.setText("Your username is " + username);
        }else{
            layoutLogin.setVisibility(View.VISIBLE);
            layoutLogged.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                final String username = edtUsername.getText().toString();

                editor.putString("username", username);
                editor.commit();

                reload();
                break;
            case R.id.btn_logout:
                editor.clear();
                editor.commit();

                reload();
                break;
        }
    }

    private void reload(){
        finish();
        startActivity(getIntent());
    }
}

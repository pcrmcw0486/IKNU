package com.example.iknu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class Setting extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onMove(View view) {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.Forum_btn :
                break;
            case R.id.Setting_btn :
                break;
            case R.id.Home_btn :
                intent = new Intent(Setting.this, Home.class);
                startActivity(intent);
                finish();
            default:
                return;
        }
    }

    public void ViewClick(View view) {
        mAuth.signOut();
        Intent intent = new Intent(Setting.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void languageChange(View view) {
        Intent intent = new Intent(Setting.this, LanguageChange.class);
        startActivity(intent);
        finish();
    }

    public void GotoTranslate(View view) {
        Intent intent = new Intent(Setting.this, Translate.class);
        startActivity(intent);
        finish();
    }
}

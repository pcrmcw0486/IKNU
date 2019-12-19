package com.example.iknu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

public class LanguageChange extends AppCompatActivity {

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change);
    }

    public void changeLanguage(View view) {
        Locale locale = null;
        Configuration config = null;
        switch(view.getId())
        {
            case R.id.Language1 :
                locale = new Locale("KO");
                break;
            case R.id.Language2 :
                locale = new Locale("EN");
                break;
            case R.id.Language3 :
                locale = new Locale("JA");
                break;

        }
        config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, null);
        Intent intent = new Intent( LanguageChange.this, Setting.class);
        startActivity(intent);
        finish();
    }

    public void onMove(View view) {
        Intent intent = new Intent( LanguageChange.this, Setting.class);
        startActivity(intent);
        finish();
    }

    /*
           Locale locale = new Locale("EN");
           Configuration config = new Configuration();
           config.locale = locale;
           getResources().updateConfiguration(config, null);*/




}

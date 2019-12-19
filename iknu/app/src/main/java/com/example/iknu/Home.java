package com.example.iknu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Switch;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);

    }


    public void onMove(View view) {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.map_btn :
                intent = new Intent(Home.this,Map_Navi.class);
                startActivity(intent);
                finish();
                break;
            case R.id.timetable_btn :
                intent = new Intent(Home.this, TimeTable.class);
                startActivity(intent);
                finish();
                break;
            case R.id.translate_btn :
                intent = new Intent(Home.this, Translate.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Forum_btn :
                intent = new Intent(Home.this, Forum.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Setting_btn :
                intent = new Intent(Home.this, Setting.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Home_btn :
                default:
                    return;
        }

        finish();
        }

}

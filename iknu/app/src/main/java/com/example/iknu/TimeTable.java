package com.example.iknu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.iknu.R;

import java.sql.Time;
import java.util.ArrayList;

public class TimeTable extends AppCompatActivity {


    int start_hour, start_minute;
    int end_hour, end_minute;
    String choose_Day="";
    Button add_lecture;
    EditText lecture_name;
    EditText professor_name;
    Spinner spinner;

    TextView Monday0;
    TextView Tuesday0;
    TextView Wendseday0;
    TextView Thursday0;
    TextView Friday0;
    TextView Saturday0;
    TextView Sunday0;

    TextView Monday1;
    TextView Tuesday1;
    TextView Wendseday1;
    TextView Thursday1;
    TextView Friday1;
    TextView Saturday1;
    TextView Sunday1;

    TextView Monday2;
    TextView Tuesday2;
    TextView Wendseday2;
    TextView Thursday2;
    TextView Friday2;
    TextView Saturday2;
    TextView Sunday2;

    TextView Monday3;
    TextView Tuesday3;
    TextView Wendseday3;
    TextView Thursday3;
    TextView Friday3;
    TextView Saturday3;
    TextView Sunday3;

    TextView Monday4;
    TextView Tuesday4;
    TextView Wendseday4;
    TextView Thursday4;
    TextView Friday4;
    TextView Saturday4;
    TextView Sunday4;

    TextView Monday5;
    TextView Tuesday5;
    TextView Wendseday5;
    TextView Thursday5;
    TextView Friday5;
    TextView Saturday5;
    TextView Sunday5;

    TextView Monday6;
    TextView Tuesday6;
    TextView Wendseday6;
    TextView Thursday6;
    TextView Friday6;
    TextView Saturday6;
    TextView Sunday6;

    TextView Monday7;
    TextView Tuesday7;
    TextView Wendseday7;
    TextView Thursday7;
    TextView Friday7;
    TextView Saturday7;
    TextView Sunday7;

    TextView Monday8;
    TextView Tuesday8;
    TextView Wendseday8;
    TextView Thursday8;
    TextView Friday8;
    TextView Saturday8;
    TextView Sunday8;

    TextView Monday9;
    TextView Tuesday9;
    TextView Wendseday9;
    TextView Thursday9;
    TextView Friday9;
    TextView Saturday9;
    TextView Sunday9;

    TextView Monday10;
    TextView Tuesday10;
    TextView Wendseday10;
    TextView Thursday10;
    TextView Friday10;
    TextView Saturday10;
    TextView Sunday10;

    TextView Monday11;
    TextView Tuesday11;
    TextView Wendseday11;
    TextView Thursday11;
    TextView Friday11;
    TextView Saturday11;
    TextView Sunday11;

    TextView Monday12;
    TextView Tuesday12;
    TextView Wendseday12;
    TextView Thursday12;
    TextView Friday12;
    TextView Saturday12;
    TextView Sunday12;

    TextView Monday13;
    TextView Tuesday13;
    TextView Wendseday13;
    TextView Thursday13;
    TextView Friday13;
    TextView Saturday13;
    TextView Sunday13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        spinner = (Spinner)findViewById(R.id.day);
        Monday0 = (TextView)findViewById(R.id.monday0);
        Tuesday0 = (TextView)findViewById(R.id.tuesday0);
        Wendseday0 = (TextView)findViewById(R.id.wednesday0);
        Thursday0 = (TextView)findViewById(R.id.tuesday0);
        Friday0 = (TextView)findViewById(R.id.friday0);
        Saturday0 = (TextView)findViewById(R.id.saturday0);
        Sunday0 = (TextView)findViewById(R.id.sunday0);

        Monday1 = (TextView)findViewById(R.id.monday1);
        Tuesday1 = (TextView)findViewById(R.id.tuesday1);
        Wendseday1 = (TextView)findViewById(R.id.wednesday1);
        Thursday1 = (TextView)findViewById(R.id.tuesday1);
        Friday1 = (TextView)findViewById(R.id.friday1);
        Saturday1 = (TextView)findViewById(R.id.saturday1);
        Sunday1 = (TextView)findViewById(R.id.sunday1);

        Monday2 = (TextView)findViewById(R.id.monday2);
        Tuesday2 = (TextView)findViewById(R.id.tuesday2);
        Wendseday2 = (TextView)findViewById(R.id.wednesday2);
        Thursday2 = (TextView)findViewById(R.id.tuesday2);
        Friday2 = (TextView)findViewById(R.id.friday2);
        Saturday2 = (TextView)findViewById(R.id.saturday2);
        Sunday2 = (TextView)findViewById(R.id.sunday2);

        Monday3 = (TextView)findViewById(R.id.monday3);
        Tuesday3 = (TextView)findViewById(R.id.tuesday3);
        Wendseday3 = (TextView)findViewById(R.id.wednesday3);
        Thursday3 = (TextView)findViewById(R.id.tuesday3);
        Friday3 = (TextView)findViewById(R.id.friday3);
        Saturday3 = (TextView)findViewById(R.id.saturday3);
        Sunday3 = (TextView)findViewById(R.id.sunday3);

        Monday4 = (TextView)findViewById(R.id.monday4);
        Tuesday4 = (TextView)findViewById(R.id.tuesday4);
        Wendseday4 = (TextView)findViewById(R.id.wednesday4);
        Thursday4 = (TextView)findViewById(R.id.tuesday4);
        Friday4 = (TextView)findViewById(R.id.friday4);
        Saturday4 = (TextView)findViewById(R.id.saturday4);
        Sunday4 = (TextView)findViewById(R.id.sunday4);

        Monday5 = (TextView)findViewById(R.id.monday5);
        Tuesday5 = (TextView)findViewById(R.id.tuesday5);
        Wendseday5 = (TextView)findViewById(R.id.wednesday5);
        Thursday5 = (TextView)findViewById(R.id.tuesday5);
        Friday5 = (TextView)findViewById(R.id.friday5);
        Saturday5 = (TextView)findViewById(R.id.saturday5);
        Sunday5 = (TextView)findViewById(R.id.sunday5);

        Monday6 = (TextView)findViewById(R.id.monday6);
        Tuesday6 = (TextView)findViewById(R.id.tuesday6);
        Wendseday6 = (TextView)findViewById(R.id.wednesday6);
        Thursday6 = (TextView)findViewById(R.id.tuesday6);
        Friday6 = (TextView)findViewById(R.id.friday6);
        Saturday6 = (TextView)findViewById(R.id.saturday6);
        Sunday6 = (TextView)findViewById(R.id.sunday6);

        Monday7 = (TextView)findViewById(R.id.monday7);
        Tuesday7 = (TextView)findViewById(R.id.tuesday7);
        Wendseday7 = (TextView)findViewById(R.id.wednesday7);
        Thursday7 = (TextView)findViewById(R.id.tuesday7);
        Friday7 = (TextView)findViewById(R.id.friday7);
        Saturday7 = (TextView)findViewById(R.id.saturday7);
        Sunday7 = (TextView)findViewById(R.id.sunday7);

        Monday8 = (TextView)findViewById(R.id.monday8);
        Tuesday8 = (TextView)findViewById(R.id.tuesday8);
        Wendseday8 = (TextView)findViewById(R.id.wednesday8);
        Thursday8 = (TextView)findViewById(R.id.tuesday8);
        Friday8 = (TextView)findViewById(R.id.friday8);
        Saturday8 = (TextView)findViewById(R.id.saturday8);
        Sunday8 = (TextView)findViewById(R.id.sunday8);

        Monday9 = (TextView)findViewById(R.id.monday9);
        Tuesday9 = (TextView)findViewById(R.id.tuesday9);
        Wendseday9 = (TextView)findViewById(R.id.wednesday9);
        Thursday9 = (TextView)findViewById(R.id.tuesday9);
        Friday9 = (TextView)findViewById(R.id.friday9);
        Saturday9 = (TextView)findViewById(R.id.saturday9);
        Sunday9 = (TextView)findViewById(R.id.sunday9);

        Monday10 = (TextView)findViewById(R.id.monday10);
        Tuesday10 = (TextView)findViewById(R.id.tuesday10);
        Wendseday10 = (TextView)findViewById(R.id.wednesday10);
        Thursday10 = (TextView)findViewById(R.id.tuesday10);
        Friday10 = (TextView)findViewById(R.id.friday10);
        Saturday10 = (TextView)findViewById(R.id.saturday10);
        Sunday10 = (TextView)findViewById(R.id.sunday10);

        Monday11 = (TextView)findViewById(R.id.monday11);
        Tuesday11 = (TextView)findViewById(R.id.tuesday11);
        Wendseday11 = (TextView)findViewById(R.id.wednesday11);
        Thursday11 = (TextView)findViewById(R.id.tuesday11);
        Friday11 = (TextView)findViewById(R.id.friday11);
        Saturday11 = (TextView)findViewById(R.id.saturday11);
        Sunday11 = (TextView)findViewById(R.id.sunday11);

        Monday12 = (TextView)findViewById(R.id.monday12);
        Tuesday12 = (TextView)findViewById(R.id.tuesday12);
        Wendseday12 = (TextView)findViewById(R.id.wednesday12);
        Thursday12 = (TextView)findViewById(R.id.tuesday12);
        Friday12 = (TextView)findViewById(R.id.friday12);
        Saturday12 = (TextView)findViewById(R.id.saturday12);
        Sunday12 = (TextView)findViewById(R.id.sunday12);

        Monday13 = (TextView)findViewById(R.id.monday13);
        Tuesday13 = (TextView)findViewById(R.id.tuesday13);
        Wendseday13 = (TextView)findViewById(R.id.wednesday13);
        Thursday13 = (TextView)findViewById(R.id.tuesday13);
        Friday13 = (TextView)findViewById(R.id.friday13);
        Saturday13 = (TextView)findViewById(R.id.saturday13);
        Sunday13 = (TextView)findViewById(R.id.sunday13);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.day,android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


    }


    public void Add_Lecture(View view) {
        add_lecture = (Button) findViewById(R.id.addLecture);
        professor_name = (EditText) findViewById(R.id.professor_name);
        lecture_name = (EditText) findViewById(R.id.lecture_name);
        //   time = (EditText) findViewById(R.id.time);
        //  day = (EditText) findViewById(R.id.day);

        if (lecture_name.getText().toString().length() == 0 || professor_name.getText().toString().length() == 0) {
            Toast.makeText(TimeTable.this, "Error", Toast.LENGTH_SHORT).show();

        } else {
            choose_Day = (String)spinner.getAdapter().getItem(spinner.getSelectedItemPosition());
            Toast.makeText(TimeTable.this, "Lecture is Added.\n" + "Lecture Name : " + lecture_name.getText().toString() +
                    "\n" + "Professor Name : " + professor_name.getText().toString() + "\n" +
                    "Lecture Time : "+choose_Day+ " " + start_hour + ":" + start_minute + "~" + end_hour + ":" + end_minute , Toast.LENGTH_LONG).show();

        }


    }

    public void timesetting(View view) {
        TimePickerDialog end = new TimePickerDialog(this, listener1, 9, 00, true);
        end.show();

        TimePickerDialog start = new TimePickerDialog(this, listener2, 8, 00, true);
        start.show();


    }

    private TimePickerDialog.OnTimeSetListener listener1 = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            end_hour = hourOfDay;
            end_minute = minute;
            //   Toast.makeText(MainActivity.this, hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();

            if (end_hour < start_hour) {
                Toast.makeText(TimeTable.this, "Error", Toast.LENGTH_SHORT).show();
            } else {
                if (end_hour == start_hour && end_minute < start_minute)
                    Toast.makeText(TimeTable.this, "Error", Toast.LENGTH_SHORT).show();
                else {
                    //        Toast.makeText(MainActivity.this, "Day : "+ choose_Day +"\nStart : " + start_hour + " : " + start_minute + "\nEnd : " + end_hour + " : " + end_minute, Toast.LENGTH_LONG).show();
                }
            }
        }


    };
    private TimePickerDialog.OnTimeSetListener listener2 = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            start_hour = hourOfDay;
            start_minute = minute;
            //    Toast.makeText(MainActivity.this, hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();

        }

    };

    public void Choose_day(View view) {
        ;
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
                intent = new Intent(TimeTable.this, Home.class);
                startActivity(intent);
                finish();
            default:
                return;
        }

        finish();
    }
}

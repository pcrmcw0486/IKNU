package com.example.iknu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
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
    String choose_Day = "";
    Button add_lecture;
    EditText lecture_name;
    EditText professor_name;
    Spinner spinner;


    TextView[] Monday = new TextView[14];
    TextView[] Tuesday = new TextView[14];
    TextView[] Wendseday = new TextView[14];
    TextView[] Thursday = new TextView[14];
    TextView[] Friday = new TextView[14];
    TextView[] Saturday = new TextView[14];
    TextView[] Sunday = new TextView[14];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        spinner = (Spinner) findViewById(R.id.day);

        Monday[0] = (TextView) findViewById(R.id.monday0);
        Monday[1] = (TextView) findViewById(R.id.monday1);
        Monday[2] = (TextView) findViewById(R.id.monday2);
        Monday[3] = (TextView) findViewById(R.id.monday3);
        Monday[4] = (TextView) findViewById(R.id.monday4);
        Monday[5] = (TextView) findViewById(R.id.monday5);
        Monday[6] = (TextView) findViewById(R.id.monday6);
        Monday[7] = (TextView) findViewById(R.id.monday7);
        Monday[8] = (TextView) findViewById(R.id.monday8);
        Monday[9] = (TextView) findViewById(R.id.monday9);
        Monday[10] = (TextView) findViewById(R.id.monday10);
        Monday[11] = (TextView) findViewById(R.id.monday11);
        Monday[12] = (TextView) findViewById(R.id.monday12);
        Monday[13] = (TextView) findViewById(R.id.monday13);

        Tuesday[0] = (TextView) findViewById(R.id.tuesday0);
        Tuesday[1] = (TextView) findViewById(R.id.tuesday1);
        Tuesday[2] = (TextView) findViewById(R.id.tuesday2);
        Tuesday[3] = (TextView) findViewById(R.id.tuesday3);
        Tuesday[4] = (TextView) findViewById(R.id.tuesday4);
        Tuesday[5] = (TextView) findViewById(R.id.tuesday5);
        Tuesday[6] = (TextView) findViewById(R.id.tuesday6);
        Tuesday[7] = (TextView) findViewById(R.id.tuesday7);
        Tuesday[8] = (TextView) findViewById(R.id.tuesday8);
        Tuesday[9] = (TextView) findViewById(R.id.tuesday9);
        Tuesday[10] = (TextView) findViewById(R.id.tuesday10);
        Tuesday[11] = (TextView) findViewById(R.id.tuesday11);
        Tuesday[12] = (TextView) findViewById(R.id.tuesday12);
        Tuesday[13] = (TextView) findViewById(R.id.tuesday13);

        Wendseday[0] = (TextView) findViewById(R.id.wednesday0);
        Wendseday[1] = (TextView) findViewById(R.id.wednesday1);
        Wendseday[2] = (TextView) findViewById(R.id.wednesday2);
        Wendseday[3] = (TextView) findViewById(R.id.wednesday3);
        Wendseday[4] = (TextView) findViewById(R.id.wednesday4);
        Wendseday[5] = (TextView) findViewById(R.id.wednesday5);
        Wendseday[6] = (TextView) findViewById(R.id.wednesday6);
        Wendseday[7] = (TextView) findViewById(R.id.wednesday7);
        Wendseday[8] = (TextView) findViewById(R.id.wednesday8);
        Wendseday[9] = (TextView) findViewById(R.id.wednesday9);
        Wendseday[10] = (TextView) findViewById(R.id.wednesday10);
        Wendseday[11] = (TextView) findViewById(R.id.wednesday11);
        Wendseday[12] = (TextView) findViewById(R.id.wednesday12);
        Wendseday[13] = (TextView) findViewById(R.id.wednesday13);


        Thursday[0] = (TextView) findViewById(R.id.thursday0);
        Thursday[1] = (TextView) findViewById(R.id.thursday1);
        Thursday[2] = (TextView) findViewById(R.id.thursday2);
        Thursday[3] = (TextView) findViewById(R.id.thursday3);
        Thursday[4] = (TextView) findViewById(R.id.thursday4);
        Thursday[5] = (TextView) findViewById(R.id.thursday5);
        Thursday[6] = (TextView) findViewById(R.id.thursday6);
        Thursday[7] = (TextView) findViewById(R.id.thursday7);
        Thursday[8] = (TextView) findViewById(R.id.thursday8);
        Thursday[9] = (TextView) findViewById(R.id.thursday9);
        Thursday[10] = (TextView) findViewById(R.id.thursday10);
        Thursday[11] = (TextView) findViewById(R.id.thursday11);
        Thursday[12] = (TextView) findViewById(R.id.thursday12);
        Thursday[13] = (TextView) findViewById(R.id.thursday13);

        Friday[0] = (TextView) findViewById(R.id.friday0);
        Friday[1] = (TextView) findViewById(R.id.friday1);
        Friday[2] = (TextView) findViewById(R.id.friday2);
        Friday[3] = (TextView) findViewById(R.id.friday3);
        Friday[4] = (TextView) findViewById(R.id.friday4);
        Friday[5] = (TextView) findViewById(R.id.friday5);
        Friday[6] = (TextView) findViewById(R.id.friday6);
        Friday[7] = (TextView) findViewById(R.id.friday7);
        Friday[8] = (TextView) findViewById(R.id.friday8);
        Friday[9] = (TextView) findViewById(R.id.friday9);
        Friday[10] = (TextView) findViewById(R.id.friday10);
        Friday[11] = (TextView) findViewById(R.id.friday11);
        Friday[12] = (TextView) findViewById(R.id.friday12);
        Friday[13] = (TextView) findViewById(R.id.friday13);


        Saturday[0] = (TextView) findViewById(R.id.saturday0);
        Saturday[1] = (TextView) findViewById(R.id.saturday1);
        Saturday[2] = (TextView) findViewById(R.id.saturday2);
        Saturday[3] = (TextView) findViewById(R.id.saturday3);
        Saturday[4] = (TextView) findViewById(R.id.saturday4);
        Saturday[5] = (TextView) findViewById(R.id.saturday5);
        Saturday[6] = (TextView) findViewById(R.id.saturday6);
        Saturday[7] = (TextView) findViewById(R.id.saturday7);
        Saturday[8] = (TextView) findViewById(R.id.saturday8);
        Saturday[9] = (TextView) findViewById(R.id.saturday9);
        Saturday[10] = (TextView) findViewById(R.id.saturday10);
        Saturday[11] = (TextView) findViewById(R.id.saturday11);
        Saturday[12] = (TextView) findViewById(R.id.saturday12);
        Saturday[13] = (TextView) findViewById(R.id.saturday13);

        Sunday[0] = (TextView) findViewById(R.id.sunday0);
        Sunday[1] = (TextView) findViewById(R.id.sunday1);
        Sunday[2] = (TextView) findViewById(R.id.sunday2);
        Sunday[3] = (TextView) findViewById(R.id.sunday3);
        Sunday[4] = (TextView) findViewById(R.id.sunday4);
        Sunday[5] = (TextView) findViewById(R.id.sunday5);
        Sunday[6] = (TextView) findViewById(R.id.sunday6);
        Sunday[7] = (TextView) findViewById(R.id.sunday7);
        Sunday[8] = (TextView) findViewById(R.id.sunday8);
        Sunday[9] = (TextView) findViewById(R.id.sunday9);
        Sunday[10] = (TextView) findViewById(R.id.sunday10);
        Sunday[11] = (TextView) findViewById(R.id.sunday11);
        Sunday[12] = (TextView) findViewById(R.id.sunday12);
        Sunday[13] = (TextView) findViewById(R.id.sunday13);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.day, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


    }


    public void Add_Lecture(View view) {
        add_lecture = (Button) findViewById(R.id.addLecture);
        professor_name = (EditText) findViewById(R.id.professor_name);
        lecture_name = (EditText) findViewById(R.id.lecture_name);
        //   time = (EditText) findViewById(R.id.time);
        //  day = (EditText) findViewById(R.id.day);

        choose_Day = (String) spinner.getAdapter().getItem(spinner.getSelectedItemPosition());
        if (lecture_name.getText().toString().length() == 0 || professor_name.getText().toString().length() == 0) {
            Toast.makeText(TimeTable.this, "Error", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(TimeTable.this, "Lecture is Added.\n" + "Lecture Name : " + lecture_name.getText().toString() +
                    "\n" + "Professor Name : " + professor_name.getText().toString() + "\n" +
                    "Lecture Time : " + choose_Day + " " + start_hour + ":" + start_minute + "~" + end_hour + ":" + end_minute, Toast.LENGTH_LONG).show();

            changeColor();

            lecture_name.setText(" ");
            lecture_name.setHint("Lecture Name");
            professor_name.setText(" ");

            professor_name.setHint("Professor_name");

        }


    }

    public void timesetting(View view) {
        TimePickerDialog end = new TimePickerDialog(this, listener1, 9, 00, true);
        end.show();

        TimePickerDialog start = new TimePickerDialog(this, listener2, 8, 00, true);
        start.show();


    }

    public void changeColor() {
        int cnt = 0;

        if (choose_Day.equals("Monday")) {
            for (int i = start_hour - 8; i < end_hour - 8; i++) {
                Monday[i].setBackgroundColor(Color.parseColor("#B2EBF2"));
                Monday[i].setTextColor(Color.parseColor("#000000"));
                if (cnt == 0) {
                    Monday[i].setText(lecture_name.getText().toString() + "\n" + professor_name.getText().toString());
                    cnt++;
                }
                else
                {
                    Monday[i].setText(" ");
                }
                Toast.makeText(TimeTable.this, "HI", Toast.LENGTH_SHORT);
            }

        } else if (choose_Day.equals("Tuesday")) {
            for (int i = start_hour - 8; i < end_hour - 8; i++) {
                Tuesday[i].setBackgroundColor(Color.parseColor("#D1C4E9"));
                Tuesday[i].setTextColor(Color.parseColor("#000000"));

                if (cnt == 0) {
                    Tuesday[i].setText(lecture_name.getText().toString() + "\n" + professor_name.getText().toString());
                    cnt++;
                }
                else
                {
                    Tuesday[i].setText(" ");
                }
            }
        } else if (choose_Day.equals("Wendseday")) {
            for (int i = start_hour - 8; i < end_hour - 8; i++) {
                Wendseday[i].setBackgroundColor(Color.parseColor("#FFECB3"));
                Wendseday[i].setTextColor(Color.parseColor("#000000"));

                if (cnt == 0) {
                    Wendseday[i].setText(lecture_name.getText().toString() + "\n" + professor_name.getText().toString());
                    cnt++;
                }
                else
                {
                    Wendseday[i].setText(" ");
                }
            }

        } else if (choose_Day.equals("Thursday")) {
            for (int i = start_hour - 8; i < end_hour - 8; i++) {
                Thursday[i].setBackgroundColor(Color.parseColor("#C8E6C9"));
                Thursday[i].setTextColor(Color.parseColor("#000000"));

                if (cnt == 0) {
                    Thursday[i].setText(lecture_name.getText().toString() + "\n" + professor_name.getText().toString());
                    cnt++;
                }
                else
                {
                    Thursday[i].setText(" ");
                }
            }

        } else if (choose_Day.equals("Friday")) {
            for (int i = start_hour - 8; i < end_hour - 8; i++) {
                Friday[i].setBackgroundColor(Color.parseColor("#B2DFDB"));
                Friday[i].setTextColor(Color.parseColor("#000000"));

                if (cnt == 0) {
                    Friday[i].setText(lecture_name.getText().toString() + "\n" + professor_name.getText().toString());
                    cnt++;
                }
                else
                {
                    Friday[i].setText(" ");
                }
            }

        } else if (choose_Day.equals("Saturday")) {
            for (int i = start_hour - 8; i < end_hour - 8; i++) {
                Saturday[i].setBackgroundColor(Color.parseColor("#FFCA28"));
                Saturday[i].setTextColor(Color.parseColor("#000000"));

                if (cnt == 0) {
                    Saturday[i].setText(lecture_name.getText().toString() + "\n" + professor_name.getText().toString());
                    cnt++;
                }
                else
                {
                    Saturday[i].setText(" ");
                }
            }

        } else if (choose_Day.equals("Sunday")) {
            for (int i = start_hour - 8; i < end_hour - 8; i++) {
                Sunday[i].setBackgroundColor(Color.parseColor("#9CCC65"));
                Sunday[i].setTextColor(Color.parseColor("#000000"));

                if (cnt == 0) {
                    Sunday[i].setText(lecture_name.getText().toString() + "\n" + professor_name.getText().toString());
                    cnt++;
                }
                else
                {
                    Sunday[i].setText(" ");
                }
            }

        }

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


    public void onMove(View view) {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.Forum_btn :
                break;
            case R.id.Setting_btn :
                intent = new Intent(TimeTable.this, Setting.class);
                startActivity(intent);
                finish();
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

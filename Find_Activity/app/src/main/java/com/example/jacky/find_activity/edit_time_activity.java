package com.example.jacky.find_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class edit_time_activity extends AppCompatActivity {

    Button btn_Ok,btn_Cancel;

    //開始時間變數宣告
    static private int Start_year;
    static private int Start_month;
    static private int Start_day;
    static private int Start_hour;
    static private int Start_minute;

    //結束時間變數宣告
    static private int End_year;
    static private int End_month;
    static private int End_day;
    static private int End_hour;
    static private int End_minute;

    static StringBuffer str_Start_Time = new StringBuffer();
    static StringBuffer str_End_Time = new StringBuffer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time_activity);

        //取得完成與返回按鈕
        btn_Ok = findViewById(R.id.btn_OK);
        btn_Cancel = findViewById(R.id.btn_Cancel);

        //取得Calender日曆
        Calendar calendar = Calendar.getInstance();


        declare_start_time(calendar);
        declare_end_time(calendar);

        //取得開始的時間
        get_Start_time();
        //取得結束的時間
        get_End_time();


    }

    private void declare_start_time(Calendar calendar) {
        Start_year = calendar.get(Calendar.YEAR);
        Start_month = calendar.get(Calendar.MONTH);
        Start_day = calendar.get(Calendar.DAY_OF_MONTH);
        Start_hour = calendar.get(Calendar.HOUR_OF_DAY);
        Start_minute = calendar.get(Calendar.MINUTE);
    }
    private void declare_end_time(Calendar calendar) {
        End_year = calendar.get(Calendar.YEAR);
        End_month = calendar.get(Calendar.MONTH);
        End_day = calendar.get(Calendar.DAY_OF_MONTH);
        End_hour = calendar.get(Calendar.HOUR_OF_DAY);
        End_minute = calendar.get(Calendar.MINUTE);
    }

    @SuppressLint("NewApi")
    private void get_Start_time() {
        DatePicker start_DatePicker = findViewById(R.id.Start_datePicker);
        start_DatePicker.init(Start_year,Start_month,Start_day,start_DatePickerListener);

        TimePicker start_TimePicker = findViewById(R.id.Start_timePicker);
        start_TimePicker.setHour(Start_hour);
        start_TimePicker.setMinute(Start_minute);
        start_TimePicker.setOnTimeChangedListener(start_TimePickerListener);
    }

    @SuppressLint("NewApi")
    private void get_End_time() {
        DatePicker end_DatePicker = findViewById(R.id.End_datePicker);
        end_DatePicker.init(Start_year,Start_month,Start_day,end_DatePickerListener);

        TimePicker end_TimePicker = findViewById(R.id.End_timePicker);
        end_TimePicker.setHour(Start_hour);
        end_TimePicker.setMinute(Start_minute);
        end_TimePicker.setOnTimeChangedListener(end_TimePickerListener);
    }


    DatePicker.OnDateChangedListener start_DatePickerListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
            Start_year = year;
            Start_month = month;
            Start_day = day;
        }
    };
    TimePicker.OnTimeChangedListener start_TimePickerListener = new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
            Start_hour = hour;
            Start_minute = minute;
        }
    };
    DatePicker.OnDateChangedListener end_DatePickerListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
            End_year = year;
            End_month = month;
            End_day = day;
        }
    };
    TimePicker.OnTimeChangedListener end_TimePickerListener = new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
            End_hour = hour;
            End_minute = minute;
        }
    };

    public void onClick(View view){
        if((Button)view == btn_Ok){

            //清空String和StringBuffer的內容
            String time="";
            str_Start_Time.delete(0,str_Start_Time.length());
            str_End_Time.delete(0,str_End_Time.length());

            getStrStart();
            getStrEnd();
            time = "開始時間: "+str_Start_Time.toString()+"\n結束時間: "+str_End_Time.toString();
            Toast.makeText(this, time, Toast.LENGTH_SHORT).show();

            Intent it = new Intent();
            it.putExtra("時間",time);
            setResult(RESULT_OK,it);
            finish();

        }
        else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private void getStrStart() {
        str_Start_Time.append(Start_year);
        str_Start_Time.append("-");
        str_Start_Time.append(Start_month);
        str_Start_Time.append("-");
        str_Start_Time.append(Start_day);
        str_Start_Time.append("   ");
        str_Start_Time.append(Start_hour);
        str_Start_Time.append(":");
        str_Start_Time.append(Start_minute);

    }

    private void getStrEnd() {
        str_End_Time.append(End_year);
        str_End_Time.append("-");
        str_End_Time.append(End_month);
        str_End_Time.append("-");
        str_End_Time.append(End_day);
        str_End_Time.append("   ");
        str_End_Time.append(End_hour);
        str_End_Time.append(":");
        str_End_Time.append(End_minute);
    }
}

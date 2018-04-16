package com.example.jacky.find_activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static java.util.Calendar.*;

public class Marker_add_activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView txvStarTime,txvEndTime,txv1,txv2;
    Calendar calendar = getInstance();
    ListView listView;
    Button btn_OK,btn_Cancel;
    private DialogInterface.OnClickListener listener;

    static String[] AC_information = new String[]{
            "時間",
            "活動名稱",
            "活動介紹",
    };
    static  String[] text = new String[]{"","",""};
    static String str_Activity_Name="",str_Activity_Introduce="",str_StartTime="",str_EndTime="",str_Location;
    //存放text陣列

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_add_activity);

        btn_OK = findViewById(R.id.btn_OK);
        btn_Cancel = findViewById(R.id.btn_Cancel);

        /*txvStarTime = findViewById(R.id.txvStarTime);
        txvEndTime = findViewById(R.id.txvEndTime);
        txv1 = findViewById(R.id.txv1);
        txv2 = findViewById(R.id.txv2);*/

       /* txv1.setOnClickListener(this);
        txv2.setOnClickListener(this);*/

        listView = (ListView) findViewById(R.id.listView);
        List<HashMap<String , String>> list = new ArrayList<>();
        //使用List存入HashMap，用來顯示ListView上面的文字。
        updateListview();

        for(int i = 0 ; i < AC_information.length ; i++){
            HashMap<String , String> hashMap = new HashMap<>();
            hashMap.put("title" , AC_information[i]);
            hashMap.put("text" , text[i]);
            //把title , text存入HashMap之中
            list.add(hashMap);
            //把HashMap存入list之中
        }

        ListAdapter listAdapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2 ,
                new String[]{"title" , "text"} ,
                new int[]{android.R.id.text1 , android.R.id.text2});
        // 5個參數 : context , List , layout , key1 & key2 , text1 & text2
        //使用ListAdapter來顯示你輸入的文字


        listView.setAdapter(listAdapter);

        //將ListAdapter設定至ListView裡面
        listView.setOnItemClickListener(Marker_add_activity.this);


    }

 /*   @Override
    public void onClick(View view) {

        if(view == txv1){
            TimePickerDialog startTimePicker = new TimePickerDialog(this,startTime,
                    calendar.get(HOUR_OF_DAY),
                    calendar.get(MINUTE),
                    true);

                    startTimePicker.setCancelable(false);
                    startTimePicker.show();

            DatePickerDialog startDatePicker = new DatePickerDialog(this,startDate,
                    calendar.get(YEAR),
                    calendar.get(MONTH),
                    calendar.get(DAY_OF_MONTH));
            startDatePicker.setCancelable(false);

            startDatePicker.show();

        }
        else if(view == txv2){
            new TimePickerDialog(this,endTime,
                    calendar.get(HOUR_OF_DAY),
                    calendar.get(MINUTE),
                    true)
                    .show();
            new DatePickerDialog(this,endDate,
                    calendar.get(YEAR),
                    calendar.get(MONTH),
                    calendar.get(DAY_OF_MONTH))
                    .show();
    }

}*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position){
            case 0:
                edit_time();
                break;
            case 1:
                edit_activity_name();
                break;
            case 2:
                edit_activity_introduce();
                break;

        }
    }

    private void edit_time() {
        Intent it = new Intent();
        it.setClass(Marker_add_activity.this, edit_time_activity.class);

        int requestCode = 101;
        startActivityForResult(it , requestCode);
    }



    private void edit_activity_name() {
        final View item = LayoutInflater.from(Marker_add_activity.this).inflate(R.layout.item_layout, null);
        new AlertDialog.Builder(this)
                .setTitle(R.string.activity_name)
                .setView(item)
                .setPositiveButton(R.string.carry_out, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick (DialogInterface dialog,int which){
                        EditText editText = (EditText) item.findViewById(R.id.edit_text);
                        String name = editText.getText().toString();

                        if (TextUtils.isEmpty(name))
                            Toast.makeText(getApplicationContext(), "資料未儲存", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "資料已儲存" + name, Toast.LENGTH_SHORT).show();
                            text[1] =name;
                            updateListview();
                    }


                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "資料未儲存", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void edit_activity_introduce() {
        final View item = LayoutInflater.from(Marker_add_activity.this).inflate(R.layout.item_layout, null);
        new AlertDialog.Builder(this)
                .setTitle(R.string.activity_name)
                .setView(item)
                .setPositiveButton(R.string.carry_out, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick (DialogInterface dialog,int which){
                        EditText editText = (EditText) item.findViewById(R.id.edit_text);
                        String introduce = editText.getText().toString();

                        if (TextUtils.isEmpty(introduce))
                            Toast.makeText(getApplicationContext(), "資料未儲存", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "資料已儲存" + introduce, Toast.LENGTH_SHORT).show();
                        text[2] = introduce;
                        updateListview();
                    }

                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "資料未儲存", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent it){
        if(requestCode == 101 && resultCode== RESULT_OK){
            text[0] = it.getStringExtra("時間");
            updateListview();
        }
    }

    //更新Listview裡面的內容
    public void updateListview() {
        List<HashMap<String , String>> list = new ArrayList<>();
        for(int i = 0 ; i < AC_information.length ; i++){
            HashMap<String , String> hashMap = new HashMap<>();
            hashMap.put("title" , AC_information[i]);
            hashMap.put("text" , text[i]);
            //把title , text存入HashMap之中
            list.add(hashMap);
            //把HashMap存入list之中
        }

        ListAdapter listAdapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2 ,
                new String[]{"title" , "text"} ,
                new int[]{android.R.id.text1 , android.R.id.text2});
        // 5個參數 : context , List , layout , key1 & key2 , text1 & text2
        //使用ListAdapter來顯示你輸入的文字


        listView.setAdapter(listAdapter);
    }
    /*DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            if(str_StartTime.isEmpty())
                str_StartTime = str_StartTime +new String("日期:"+y+"/"+(m+1)+"/"+d+"\n");
            else{

                str_StartTime="";
                txvStarTime.setText("");
                str_StartTime =str_StartTime +new String("日期:"+y+"/"+(m+1)+"/"+d+"\n");
            }

        }
    };

    TimePickerDialog.OnTimeSetListener startTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            str_StartTime = str_StartTime + new String("時間:"+h+":"+m);
            txvStarTime.setText(txvStarTime.getText() + str_StartTime);
        }
    };
    DatePickerDialog.OnDateSetListener endDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            if(str_EndTime.isEmpty())
                str_EndTime = new String("日期:"+y+"/"+(m+1)+"/"+d+"\n");
            else{
                str_EndTime="";
                txvEndTime.setText("");
                str_EndTime =  str_EndTime + new String("日期:"+y+"/"+(m+1)+"/"+d+"\n");
            }


        }
    };
    TimePickerDialog.OnTimeSetListener endTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            str_EndTime = str_EndTime + new String("時間:"+h+":"+m);
            txvEndTime.setText(txvEndTime.getText() + str_EndTime);
        }
    };*/

    public void btn_onClick(View view){

        if((Button)view == btn_OK){
            Intent it = new Intent();
            it.putExtra("Title",text[0]);
            it.putExtra("Snippet",text[1]);

           finish();
           cleatText();
        }
        else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private void cleatText() {
        for(int i =0 ; i < text.length ; i++)
            text[i]="";
    }
}

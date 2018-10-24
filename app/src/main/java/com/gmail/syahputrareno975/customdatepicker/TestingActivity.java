package com.gmail.syahputrareno975.customdatepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.gmail.syahputrareno975.customdatepickerlib.CustomDatePicker;


public class TestingActivity extends AppCompatActivity implements View.OnClickListener {

    Button showDIalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        InitiationWidget();
    }

    void InitiationWidget(){
        showDIalog = findViewById(R.id.buttonOpenCalendar);
        showDIalog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            CustomDatePicker datePicker = new CustomDatePicker(this);
            datePicker.setOnDateChooseListener(new CustomDatePicker.OnDateChooseListener() {
                @Override
                public void ChoosedDate(int day, int month, int year) {
                    showDIalog.setText(day+"-"+month+"-"+year);
                }
            });
            datePicker.OpenDialog();

    }
}

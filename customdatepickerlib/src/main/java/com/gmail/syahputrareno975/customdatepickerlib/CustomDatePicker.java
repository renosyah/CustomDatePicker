package com.gmail.syahputrareno975.customdatepickerlib;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.gmail.syahputrareno975.customdatepickerlib.obj.DatePickerObj;
import com.gmail.syahputrareno975.customdatepickerlib.res.CustomDatePickerRes;
import static com.gmail.syahputrareno975.customdatepickerlib.res.CustomDatePickerRes.GetMonthName;
import static com.gmail.syahputrareno975.customdatepickerlib.res.CustomDatePickerRes.MakeYearList;
import static com.gmail.syahputrareno975.customdatepickerlib.res.CustomDatePickerRes.OpenYearsList;
import static com.gmail.syahputrareno975.customdatepickerlib.res.CustomDatePickerRes.dayToSimpleList;

public class CustomDatePicker {

    Context context;
    int res = R.layout.custom_alert_dialog_date_picker;
    OnDateChooseListener listener;

    AlertDialog dialog;

    TextView ArrowLeft;
    TextView MonthNameAndYear;
    TextView ArrowRight;
    GridView gridDay;

    DatePickerObj data = new DatePickerObj();

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            data.days.clear();

            if (v == ArrowLeft){
                data.month -= 1;
                checkMonth(data.month);

            }else if (v == ArrowRight){
                data.month += 1;
                checkMonth(data.month);

            }else if (v == MonthNameAndYear){
                OpenYearsList(context,MakeYearList(data.year),choosedYear).show();
            }

        }
    };

    private void checkMonth(int month){

        if (month > 11){
            data.month = 0;
            data.year += 1;

        } else if (month < 0){
            data.month = 11;
            data.year -= 1;
        }
        data = CustomDatePickerRes.SetDate(data);

        RefreshDate();
    }

    AdapterView.OnItemClickListener itemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            listener.ChoosedDate(data.days.get(position).day,(data.month+1),data.year);
            dialog.dismiss();
        }
    };

    DialogInterface.OnClickListener choosedYear = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            data.year = Integer.parseInt(MakeYearList(data.year)[i]+"");
            data = CustomDatePickerRes.SetDate(data);

            RefreshDate();

            dialogInterface.dismiss();
        }
    };

    private void RefreshDate(){
        MonthNameAndYear.setText(GetMonthName(data.month) + " "+data.year);
        gridDay.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,dayToSimpleList(data.days)));

    }

    public CustomDatePicker(Context context) {
        this.context = context;
        data = CustomDatePickerRes.SetDateToNow();
    }

    public CustomDatePicker(Context context, int month,int year) {
        this.context = context;
        this.data.month = month;
        this.data.year = year;
        data = CustomDatePickerRes.SetDate(data);
    }

    public void setOnDateChooseListener(OnDateChooseListener listener) {
        this.listener = listener;
    }

    public void OpenDialog(){
        View v = ((Activity)context).getLayoutInflater().inflate(res,null);

        dialog = new AlertDialog.Builder(context)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();


        gridDay = v.findViewById(R.id.GridviewDay);
        gridDay.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,dayToSimpleList(data.days)));
        gridDay.setOnItemClickListener(itemClick);

        ArrowLeft = v.findViewById(R.id.TextArrowLeft);
        ArrowLeft.setOnClickListener(click);

        MonthNameAndYear = v.findViewById(R.id.TexViewMonthNameWithYear);
        MonthNameAndYear.setText(GetMonthName(data.month) + " "+data.year);
        MonthNameAndYear.setOnClickListener(click);

        ArrowRight = v.findViewById(R.id.TextArrowRight);
        ArrowRight.setOnClickListener(click);

        dialog.setView(v);
        dialog.show();


    }



    public interface OnDateChooseListener{
        void ChoosedDate(int day,int month,int year);
    }


}

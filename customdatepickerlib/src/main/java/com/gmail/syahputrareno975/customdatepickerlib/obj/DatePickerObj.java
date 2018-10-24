package com.gmail.syahputrareno975.customdatepickerlib.obj;

import java.io.Serializable;
import java.util.ArrayList;

public class DatePickerObj implements Serializable {

    public int month = 0;
    public int year = 0;
    public ArrayList<DayObj> days = new ArrayList<>();

    public DatePickerObj cloneDate() {
        DatePickerObj clone = new DatePickerObj();
        for (DayObj data : this.days) {
            clone.days.add(data.cloneDay());
        }
        clone.month = this.month;
        clone.year = this.year;
        return clone;
    }

    @Override
    public String toString() {
        return "DatePickerObj{" +
                "month=" + month +
                ", year=" + year +
                '}';
    }
}

package com.gmail.syahputrareno975.customdatepickerlib.obj;

import java.io.Serializable;

public class DayObj implements Serializable {
    public int day = 0;

    public DayObj cloneDay() {
        DayObj clone = new DayObj();
        clone.day = this.day;
        return clone;
    }
}

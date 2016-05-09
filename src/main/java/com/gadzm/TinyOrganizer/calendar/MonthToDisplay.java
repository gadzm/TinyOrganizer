package com.gadzm.TinyOrganizer.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthToDisplay {

    private final List<Integer> month = new ArrayList<Integer>();
    private final Calendar calendar;

    public MonthToDisplay() {
        calendar = Calendar.getInstance();
        fillMonth();
    }

    private void fillMonth() {
        this.month.clear();
        Calendar tmp = calendar;
        tmp.set(Calendar.DAY_OF_MONTH, 1);
        int j = 0;
        if (tmp.get(Calendar.DAY_OF_WEEK) == 1) {
            for (int i = 0; i < 6; i++, j++) {
                this.month.add(null);
            }
        } else {
            for (int i = 0; i < tmp.get(Calendar.DAY_OF_WEEK) - 2; i++, j++) {
                this.month.add(null);
            }
        }
        for (int i = 0; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++, j++) {
            this.month.add(i + 1);
        }
        while (j <= 42) {
            this.month.add(null);
            j++;
        }
    }

    public Integer getDay(int row, int column) {
        return this.month.get((row * 7) + column);
    }

    public void addMonth(int i) {
        this.calendar.add(Calendar.MONTH, i);
        fillMonth();
    }

    public void addYear(int i) {
        this.calendar.add(Calendar.YEAR, i);
        fillMonth();
    }

    public Calendar getDate(int i) {
        Calendar tmp = this.calendar;
        tmp.set(Calendar.DAY_OF_MONTH, i);
        return tmp;
    }
}

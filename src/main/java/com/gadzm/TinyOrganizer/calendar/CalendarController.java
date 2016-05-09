package com.gadzm.TinyOrganizer.calendar;

public class CalendarController {

    private CalendarTable table;
    private MonthToDisplay currentMonth;
    public CalendarController(CalendarTable table, MonthToDisplay currentMonth) {
        this.table = table;
        this.currentMonth = currentMonth;
    }


    public void neighbourMonth(boolean dir) {
        this.table.clearSelection();
        if (dir) {
            this.currentMonth.addMonth(1);
            this.table.updateTable();
            return;
        }
        this.currentMonth.addMonth(-1);
        this.table.updateTable();
    }

    /*
	 * przesuniecie kalendarza o jeden rok
     */
    public void neighbourYear(boolean dir) {
        this.table.clearSelection();
        if (dir) {
           this.currentMonth.addYear(1);
            this.table.updateTable();
            return;
        }
        this.currentMonth.addYear(-1);
        this.table.updateTable();
    }
}

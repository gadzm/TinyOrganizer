package com.gadzm.TinyOrganizer;

public class CalendarController {

    private CalendarTable table;

    public CalendarController(CalendarTable table) {
        this.table = table;
    }

    /*
	 * przesuniecie kalendarza o jeden miesiac 
     */
    public void neighbourMonth(boolean dir) {
        this.table.clearSelection();
        if (dir) {
            MainWindow.currentMonth.addMonth(1);
            this.table.updateTable();
            return;
        }
        MainWindow.currentMonth.addMonth(-1);
        this.table.updateTable();
    }

    /*
	 * przesuniecie kalendarza o jeden rok
     */
    public void neighbourYear(boolean dir) {
        this.table.clearSelection();
        if (dir) {
            MainWindow.currentMonth.addYear(1);
            this.table.updateTable();
            return;
        }
        MainWindow.currentMonth.addYear(-1);
        this.table.updateTable();
    }
}

package com.gadzm.TinyOrganizer.calendar;

import javax.swing.JTable;

public class CalendarTable extends JTable {

    MonthToDisplay currentMonth;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CalendarTable(MonthToDisplay currentMonth) {
        this.currentMonth = currentMonth;
        prepareTable();
    }

    private void prepareTable() {
        setModel(new CalendarTableModel(this.currentMonth));
        setRowSelectionAllowed(false);
        setRowHeight(30);
        setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        CalendarTableRenderer renderer = new CalendarTableRenderer(currentMonth);
        try {
            setDefaultRenderer(Class.forName("java.lang.Integer"), renderer);
        } catch (ClassNotFoundException e1) {
            
        }

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setEnabled(false);
    }

    public void updateTable() {
        setModel(new CalendarTableModel(this.currentMonth));
    }

    public void Action() {

    }
}

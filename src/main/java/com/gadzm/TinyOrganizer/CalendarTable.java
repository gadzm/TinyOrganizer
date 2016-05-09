package com.gadzm.TinyOrganizer;

import javax.swing.JTable;

public class CalendarTable extends JTable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CalendarTable() {
        setModel(new CalendarTableModel(MainWindow.currentMonth));
        setRowSelectionAllowed(false);
        setRowHeight(30);
        setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        CalendarTableRenderer renderer = new CalendarTableRenderer(MainWindow.currentMonth);
        try {
            setDefaultRenderer(Class.forName("java.lang.Integer"), renderer);
        } catch (ClassNotFoundException e1) {
        }

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setEnabled(false);
    }

    public void updateTable() {
        setModel(new CalendarTableModel(MainWindow.currentMonth));
    }

    public void Action() {

    }
}

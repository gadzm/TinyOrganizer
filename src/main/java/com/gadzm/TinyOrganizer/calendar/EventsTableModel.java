package com.gadzm.TinyOrganizer.calendar;

import com.gadzm.TinyOrganizer.events.Event;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class EventsTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private final SimpleDateFormat addEventDateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
    private final String[] columnNames = {"Tytuł", "Data", "Koniec", "Miejsce", "Treść", "Przypomnienie"};
    private final List<Event> events;

    public EventsTableModel(List<Event> events) {
        this.events = events;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return events.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (events == null) {
            return null;
        }
        switch (columnIndex) {
            case 0:
                return events.get(rowIndex).getTitle();
            case 1:
                return addEventDateFormat.format(events.get(rowIndex).getEventDate().getTime());
            case 2:
                return addEventDateFormat.format(events.get(rowIndex).getEndDate().getTime());
            case 3:
                return events.get(rowIndex).getPlace();
            case 4:
                return events.get(rowIndex).getContent();
            case 5:
                if (events.get(rowIndex).isReminded()) {
                    return addEventDateFormat.format(events.get(rowIndex).getReminderDate().getTime());
                } else {
                    return "";
                }
            default:
                return "";
        }
    }
}

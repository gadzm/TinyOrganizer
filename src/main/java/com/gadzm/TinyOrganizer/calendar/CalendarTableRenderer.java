package com.gadzm.TinyOrganizer.calendar;

import com.gadzm.TinyOrganizer.events.EventContainer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

class CalendarTableRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    private final MonthToDisplay currentMonth;

    public CalendarTableRenderer(MonthToDisplay month) {
        this.currentMonth = month;
        setHorizontalAlignment(SwingConstants.CENTER);

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Integer) {
            cell.setFont(new Font("Century", Font.BOLD, 14));
            if (EventContainer.GetInstance().isDayWithEvent(currentMonth.getDate(((Integer) value)))) {
                cell.setBackground(new Color(139, 182, 208));
                cell.setForeground(new Color(0, 0, 0));
            } else {
                cell.setBackground(new Color(245, 245, 245));
                cell.setForeground(new Color(0, 0, 0));
            }
        } else {
            cell.setBackground(new Color(245, 245, 245));
            cell.setForeground(new Color(0, 0, 0));
        }
        return cell;
    }
}

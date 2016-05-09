package com.gadzm.TinyOrganizer;

import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class DailyTable extends JTable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DailyTable(JLayeredPane panelDay) {
        setBounds(0, 0, panelDay.getWidth() - 1, panelDay.getHeight() - 1);
        setModel(new TAbleModel());
        setBackground(null);
        getColumnModel().getColumn(0).setPreferredWidth(2);
        getColumnModel().getColumn(1).setPreferredWidth(480);
        setRowHeight(15);
        setFont(new Font("Arial", Font.BOLD, 12));
        setEnabled(false);
    }

    private class TAbleModel extends AbstractTableModel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public int getColumnCount() {

            return 2;
        }

        public int getRowCount() {
            return 48;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 1) {
                return null;
            }
            if (rowIndex % 2 == 0) {
                return rowIndex / 2 + ":00";
            }
            return null;
        }
    }
}

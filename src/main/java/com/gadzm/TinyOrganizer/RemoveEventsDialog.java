package com.gadzm.TinyOrganizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RemoveEventsDialog extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyyHHmm", Locale.getDefault());
    private JTextField fieldBefore;
    private JTextField fieldAfter;
    private JCheckBox chckbxBefore;
    private JCheckBox chckbxAfter;
    private JButton btnFilter;

    public RemoveEventsDialog(JFrame parent) {
        super(parent, "Filtruj Wydarzenia");
        setTitle("Usuń Wydarzenia");
        this.setVisible(true);
        this.setResizable(false);
        prepare(parent);
    }

    private final void prepare(JFrame parent) {
        this.setBounds(0, 0, 217, 194);
        this.setLocationRelativeTo(parent);
        getContentPane().setLayout(null);

        fieldBefore = new JTextField();
        fieldBefore.setToolTipText("Format daty:\r\nddMMrrrr");
        fieldBefore.setBounds(20, 43, 86, 20);
        getContentPane().add(fieldBefore);
        fieldBefore.setColumns(10);

        fieldAfter = new JTextField();
        fieldAfter.setToolTipText("Format daty:\r\nddMMrrrr");
        fieldAfter.setBounds(20, 74, 86, 20);
        getContentPane().add(fieldAfter);
        fieldAfter.setColumns(10);

        chckbxBefore = new JCheckBox("Przed");
        chckbxBefore.setBounds(112, 42, 62, 23);
        getContentPane().add(chckbxBefore);

        chckbxAfter = new JCheckBox("Po");
        chckbxAfter.setBounds(112, 73, 62, 23);
        getContentPane().add(chckbxAfter, BorderLayout.WEST);

        btnFilter = new JButton("Usuń");
        btnFilter.setBounds(26, 105, 159, 50);
        getContentPane().add(btnFilter);
        btnFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventFilter();
            }
        });

        JLabel lblNewLabel = new JLabel("Podaj daty graniczne (dd/MM/yyyy)");
        lblNewLabel.setBounds(10, 21, 195, 14);
        getContentPane().add(lblNewLabel);
    }

    private void eventFilter() {
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        if (this.chckbxBefore.isSelected()) {
            if (this.chckbxAfter.isSelected()) {
                String dateBefore = this.fieldBefore.getText() + "0000";
                String dateAfter = this.fieldAfter.getText() + "0000";
                try {
                    calBefore.setTime(dateFormat.parse(dateBefore));
                    calAfter.setTime(dateFormat.parse(dateAfter));
                    EventContainer.GetInstance().removeEventsBetween(calAfter, calBefore);
                    setVisible(false);
                    return;
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(getContentPane(), "Wprowadzona data ma niepoprawny format", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
            String dateBefore = this.fieldBefore.getText() + "0000";
            try {
                calBefore.setTime(dateFormat.parse(dateBefore));
                EventContainer.GetInstance().removeEventsBefore(calBefore);
                setVisible(false);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(getContentPane(), "Wprowadzona data ma niepoprawny format", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        } else if (this.chckbxAfter.isSelected()) {
            String dateAfter = this.fieldAfter.getText() + "0000";
            try {
                calAfter.setTime(dateFormat.parse(dateAfter));
                EventContainer.GetInstance().removeEventsAfter(calAfter);
                setVisible(false);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(getContentPane(), "Wprowadzona data ma niepoprawny format", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}

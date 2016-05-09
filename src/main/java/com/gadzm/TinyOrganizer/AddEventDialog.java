package com.gadzm.TinyOrganizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

public class AddEventDialog extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField fieldTitle;
    private JTextField fieldDate;
    private JTextField fieldTime;
    private JTextField fieldRemind;
    private JCheckBox chckbxRemind;
    private JTextArea tAreaMessage;
    private JButton btnAddEvent;
    private JButton btnClose;
    private JTextField fieldPlace;
    private JComboBox<?> remindBox = new JComboBox<Object>();
    private final Calendar selectedDate = Calendar.getInstance();
    private Calendar endDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    private final SimpleDateFormat dateFormatPred = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private JTextField fieldDuration;
    private JComboBox<?> durationBox;

    /**
     * @wbp.parser.constructor
     */
    public AddEventDialog(JFrame parent) {
        super(parent, "Dodaj wydarzenie");
        this.setVisible(true);
        this.setResizable(false);
        prepare(parent);
    }

    /*
	 * konstruktor okna dialogowego po wybraniu daty z kalendarza
     */
    public AddEventDialog(JFrame parent, Calendar date) {
        super(parent, "Dodaj wydarzenie");
        this.setVisible(true);
        this.setResizable(false);
        prepare(parent);
        this.fieldDate.setText(dateFormatPred.format(date.getTime()));
    }

    public final void prepare(JFrame s) {
        this.setBounds(400, 200, 400, 309);
        this.setLocationRelativeTo(s);
        getContentPane().setLayout(null);
        JLabel lblNazwa = new JLabel("Tytuł");
        lblNazwa.setFont(new Font("Arial", Font.BOLD, 12));
        lblNazwa.setHorizontalAlignment(SwingConstants.CENTER);
        lblNazwa.setBounds(5, 10, 86, 14);
        getContentPane().add(lblNazwa);

        fieldTitle = new JTextField("bez_nazwy");
        fieldTitle.setBounds(5, 30, 86, 20);
        getContentPane().add(fieldTitle);
        fieldTitle.setColumns(10);

        JLabel lblData = new JLabel("Data (dd/MM/rrrr)");
        lblData.setFont(new Font("Arial", Font.BOLD, 12));
        lblData.setBounds(101, 10, 103, 14);
        lblData.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblData);

        fieldDate = new JTextField();
        fieldDate.setBounds(101, 30, 103, 20);
        getContentPane().add(fieldDate);
        fieldDate.setColumns(10);

        JLabel lblGodzina = new JLabel("Godzina (HH:mm)");
        lblGodzina.setFont(new Font("Arial", Font.BOLD, 12));
        lblGodzina.setBounds(207, 10, 101, 14);
        lblGodzina.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblGodzina);

        fieldTime = new JTextField();
        fieldTime.setBounds(230, 30, 54, 20);
        getContentPane().add(fieldTime);
        fieldTime.setColumns(10);

        JLabel lblMiejsce = new JLabel("Miejsce");
        lblMiejsce.setFont(new Font("Arial", Font.BOLD, 12));
        lblMiejsce.setBounds(324, 10, 46, 14);
        lblMiejsce.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblMiejsce);

        fieldPlace = new JTextField();
        fieldPlace.setBounds(310, 30, 74, 20);
        getContentPane().add(fieldPlace);
        fieldPlace.setColumns(10);

        JLabel lblTre = new JLabel("Treść");
        lblTre.setFont(new Font("Arial", Font.BOLD, 12));
        lblTre.setBounds(5, 83, 55, 14);
        lblTre.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTre);

        tAreaMessage = new JTextArea();
        tAreaMessage.setFont(new Font("Monospaced", Font.PLAIN, 14));
        tAreaMessage.setBounds(4, 108, 200, 100);
        getContentPane().add(tAreaMessage);

        btnAddEvent = new JButton("Dodaj");
        btnAddEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    addEvent();

                    setVisible(false);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(getContentPane(), "Wprowadzono niepoprawny format daty lub godziny!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAddEvent.setBounds(35, 219, 150, 50);
        getContentPane().add(btnAddEvent);
        btnClose = new JButton("Anuluj");
        btnClose.setBounds(199, 219, 150, 50);
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        getContentPane().add(btnClose);

        fieldRemind = new JTextField();
        fieldRemind.setBounds(252, 163, 32, 20);
        getContentPane().add(fieldRemind);
        fieldRemind.setEnabled(false);

        chckbxRemind = new JCheckBox("Przypomnienie");
        chckbxRemind.setToolTipText("Okres czasu przed wydarzeniem");
        chckbxRemind.setBounds(252, 133, 97, 23);
        getContentPane().add(chckbxRemind);
        chckbxRemind.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fieldRemind.setEnabled(true);
                    remindBox.setEnabled(true);
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    fieldRemind.setEnabled(false);
                    remindBox.setEnabled(false);
                }

            }
        });

        String[] remindsRange = {"minuty", "godziny", "dni"};
        remindBox = new JComboBox<Object>(remindsRange);
        remindBox.setBounds(293, 163, 67, 20);
        getContentPane().add(remindBox);
        remindBox.setEnabled(false);

        JLabel lblCzasTrwania = new JLabel("czas trwania");
        lblCzasTrwania.setFont(new Font("Arial", Font.BOLD, 12));
        lblCzasTrwania.setBounds(150, 64, 74, 14);
        getContentPane().add(lblCzasTrwania);

        fieldDuration = new JTextField();
        fieldDuration.setBounds(230, 61, 54, 20);
        getContentPane().add(fieldDuration);
        fieldDuration.setColumns(10);
        fieldDuration.setText("1");

        String[] durationRange = {"minuty", "godziny"};
        durationBox = new JComboBox<Object>(durationRange);
        durationBox.setBounds(310, 61, 74, 20);
        durationBox.setSelectedIndex(1);
        getContentPane().add(durationBox);

    }

    private void addEvent() throws ParseException {
        String day = this.fieldDate.getText();
        String hour = this.fieldTime.getText();
        String ddate = day + " " + hour;
        selectedDate.setTime(dateFormat.parse(ddate));
        String title = this.fieldTitle.getText();
        String place = this.fieldPlace.getText();
        String content = this.tAreaMessage.getText();

        if (chckbxRemind.isSelected()) {
            try {
                calculateDuration();
                EventContainer.GetInstance().addEvent(title, content, place, selectedDate, endDate, calculateRemind());
            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(getContentPane(), "Wprowadzono nieprawidłowa wartośc przypomnienia lub czasu trwania wydarzenia", "Błąd", JOptionPane.ERROR_MESSAGE);
            } catch (SameDateException e) {
                JOptionPane.showMessageDialog(getContentPane(), "W tym czasie jest zaplanowano już inne wydarzenie!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(getContentPane(), "Dodano wydarzenie", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                calculateDuration();
                EventContainer.GetInstance().addEvent(title, content, place, selectedDate, endDate);
            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(getContentPane(), "Wprowadzono nieprawidłowa wartośc czasu trwania wydarzenia", "Błąd", JOptionPane.ERROR_MESSAGE);
            } catch (SameDateException e) {
                JOptionPane.showMessageDialog(getContentPane(), "W tym czasie jest zaplanowano już inne wydarzenie!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(getContentPane(), "Dodano wydarzenie", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Calendar calculateRemind() throws NumberFormatException {
        int value = Integer.parseUnsignedInt(this.fieldRemind.getText());
        Calendar remindDate = new GregorianCalendar();
        remindDate.setTime(selectedDate.getTime());

        if (remindBox.getSelectedIndex() == 0) {
            remindDate.add(Calendar.MINUTE, -value);

        }

        if (remindBox.getSelectedIndex() == 1) {
            remindDate.add(Calendar.HOUR, -value);
        }

        if (remindBox.getSelectedIndex() == 2) {
            remindDate.add(Calendar.DAY_OF_YEAR, -value);
        }
        return remindDate;
    }

    private void calculateDuration() throws NumberFormatException {
        this.endDate = new GregorianCalendar();
        endDate.setTime(this.selectedDate.getTime());
        if (this.fieldDuration.getText().equals("")) {
            return;
        }
        Integer value = Integer.parseUnsignedInt(this.fieldDuration.getText());
        if (durationBox.getSelectedIndex() == 0) {//minuty
            if (value >= 240) {
                endDate.add(Calendar.MINUTE, 240);
                return;
            }
            if (value <= 30) {
                endDate.add(Calendar.MINUTE, 30);
                return;
            } else {
                endDate.add(Calendar.MINUTE, value);
                return;
            }
        }
        if (durationBox.getSelectedIndex() == 1) {
            if (value >= 4) {
                endDate.add(Calendar.HOUR, 4);
            } else {
                endDate.add(Calendar.HOUR, value);
            }
        }

    }
}

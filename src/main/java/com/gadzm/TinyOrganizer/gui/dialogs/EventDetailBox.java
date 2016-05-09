package com.gadzm.TinyOrganizer.gui.dialogs;

import com.gadzm.TinyOrganizer.events.Event;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;

public class EventDetailBox extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static JTextField textTitle;
    private static JTextField textStart;
    private static JTextField textEnd;
    private static JTextField textRemind;
    private static JTextField textPlace;
    private static JTextPane textConent;
    private static JLabel lblNewLabel;
    private static JLabel lblNewLabel_1;
    private static JLabel lblCzasTrwania;
    private static JLabel lblDataPowiadomienia;
    private static JLabel lblMiejsce;
    private static JLabel lblNewLabel_2;

    private EventDetailBox(int x, int y) {

        setBounds(x, y, 362, 269);
        setLayout(null);
        Border border = BorderFactory.createLineBorder(Color.black);
        setBorder(border);
        lblNewLabel = new JLabel("Tytuł");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(22, 22, 98, 14);
        add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Data rozpoczęcia");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(22, 47, 98, 14);
        add(lblNewLabel_1);

        lblCzasTrwania = new JLabel("Koniec");
        lblCzasTrwania.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblCzasTrwania.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCzasTrwania.setBounds(22, 72, 98, 14);
        add(lblCzasTrwania);

        lblDataPowiadomienia = new JLabel("Data powiadomienia");
        lblDataPowiadomienia.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblDataPowiadomienia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDataPowiadomienia.setBounds(0, 97, 120, 14);
        add(lblDataPowiadomienia);

        lblMiejsce = new JLabel("Miejsce");
        lblMiejsce.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblMiejsce.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMiejsce.setBounds(22, 122, 98, 14);
        add(lblMiejsce);

        lblNewLabel_2 = new JLabel("Treść");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(22, 147, 98, 14);
        add(lblNewLabel_2);

        textTitle = new JTextField();
        textTitle.setBackground(SystemColor.menu);
        textTitle.setBounds(130, 19, 109, 20);
        add(textTitle);
        textTitle.setColumns(10);

        textStart = new JTextField();
        textStart.setBackground(SystemColor.menu);
        textStart.setBounds(130, 44, 109, 20);
        add(textStart);
        textStart.setColumns(10);

        textEnd = new JTextField();
        textEnd.setBackground(SystemColor.menu);
        textEnd.setBounds(130, 69, 109, 20);
        add(textEnd);
        textEnd.setColumns(10);

        textRemind = new JTextField();
        textRemind.setBackground(SystemColor.menu);
        textRemind.setBounds(130, 94, 109, 20);
        add(textRemind);
        textRemind.setColumns(10);

        textPlace = new JTextField();
        textPlace.setBackground(SystemColor.menu);
        textPlace.setBounds(130, 119, 109, 20);
        add(textPlace);
        textPlace.setColumns(10);

        textConent = new JTextPane();
        textConent.setBackground(SystemColor.menu);
        textConent.setBounds(130, 147, 200, 103);
        textConent.setBorder(border);
        add(textConent);
        EventDetailBox.hideContent();
    }

    public static EventDetailBox getEventDetailBox(int x, int y) {
        return new EventDetailBox(x, y);
    }

    static public void fillData(Event event) {
        showContent();
        textTitle.setText(event.getTitle());
        textStart.setText(dateFormat.format(event.getEventDate().getTime()));
        textEnd.setText(dateFormat1.format(event.getEndDate().getTime()));
        textPlace.setText(event.getPlace());
        try {
            textRemind.setText(dateFormat.format(event.getReminderDate().getTime()));
        } catch (NullPointerException e) {
            textRemind.setText("brak powiadomienia");
        }
        textConent.setText(event.getContent());
    }

    static public void hideContent() {
        textTitle.setVisible(false);
        textStart.setVisible(false);
        textEnd.setVisible(false);
        textRemind.setVisible(false);
        textPlace.setVisible(false);
        textConent.setVisible(false);
        lblNewLabel.setVisible(false);
        lblNewLabel_1.setVisible(false);
        lblCzasTrwania.setVisible(false);
        lblDataPowiadomienia.setVisible(false);
        lblMiejsce.setVisible(false);
        lblNewLabel_2.setVisible(false);
    }

    static private void showContent() {
        textTitle.setVisible(true);
        textStart.setVisible(true);
        textEnd.setVisible(true);
        textRemind.setVisible(true);
        textPlace.setVisible(true);
        textConent.setVisible(true);
        lblNewLabel.setVisible(true);
        lblNewLabel_1.setVisible(true);
        lblCzasTrwania.setVisible(true);
        lblDataPowiadomienia.setVisible(true);
        lblMiejsce.setVisible(true);
        lblNewLabel_2.setVisible(true);
    }
}

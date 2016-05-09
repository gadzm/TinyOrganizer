package com.gadzm.TinyOrganizer;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import javax.swing.JTextPane;

public class RemindDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField textTitle;
    private JTextField textTime;
    private JTextField textPlace;
    private JTextPane textArea;
    private final Event eventToRemind;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
    private final JFrame parent;

    public RemindDialog(JFrame parent, Event eventToRemind) {
        this.eventToRemind = eventToRemind;
        this.parent = parent;
        prepare();

    }

    private void prepare() {
        getContentPane().setLayout(null);
        setTitle("Przypomnienie o zdarzeniu");
        setBounds(400, 200, 283, 211);
        setResizable(false);
        setLocationRelativeTo(this.parent);

        JLabel lblNewLabel = new JLabel("Nazwa zdarzenia");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(10, 11, 112, 14);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Data");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(76, 36, 46, 14);
        getContentPane().add(lblNewLabel_1);

        JLabel lblMiejsce = new JLabel("Miejsce");
        lblMiejsce.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMiejsce.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMiejsce.setBounds(52, 61, 70, 14);
        getContentPane().add(lblMiejsce);

        JLabel lblTre = new JLabel("Treść");
        lblTre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTre.setBounds(52, 86, 70, 14);
        getContentPane().add(lblTre);

        textTitle = new JTextField();
        textTitle.setEnabled(true);
        textTitle.setEditable(false);
        textTitle.setBounds(136, 9, 132, 20);
        textTitle.setText(this.eventToRemind.getTitle());
        getContentPane().add(textTitle);
        textTitle.setColumns(10);

        textTime = new JTextField();
        textTime.setEnabled(true);
        textTime.setEditable(false);
        textTime.setColumns(10);
        textTime.setBounds(136, 34, 132, 20);
        getContentPane().add(textTime);
        textTime.setText(dateFormat.format(this.eventToRemind.getEventDate().getTime()));

        textPlace = new JTextField();
        textPlace.setEnabled(true);
        textPlace.setEditable(false);
        textPlace.setColumns(10);
        textPlace.setBounds(136, 59, 132, 20);
        getContentPane().add(textPlace);
        textPlace.setText(this.eventToRemind.getPlace());

        textArea = new JTextPane();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textArea.setBackground(SystemColor.controlHighlight);
        textArea.setEditable(false);
        textArea.setBounds(136, 87, 132, 89);
        getContentPane().add(textArea);
        textArea.setText(this.eventToRemind.getContent());
        setVisible(true);
    }

}

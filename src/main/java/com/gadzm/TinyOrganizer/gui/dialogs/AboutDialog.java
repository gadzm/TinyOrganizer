package com.gadzm.TinyOrganizer.gui.dialogs;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        prepareDialog();
        setLocationRelativeTo(parent);
    }

    private void prepareDialog() {
        setTitle("O programie");
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Programowanie Komponentowe");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(43, 11, 257, 17);
        getContentPane().add(lblNewLabel);

        JLabel lblProjektZaliczeniowyPrzedmiotu = new JLabel("Projekt zaliczeniowy przedmiotu");
        lblProjektZaliczeniowyPrzedmiotu.setHorizontalAlignment(SwingConstants.CENTER);
        lblProjektZaliczeniowyPrzedmiotu.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblProjektZaliczeniowyPrzedmiotu.setBounds(43, 28, 257, 17);
        getContentPane().add(lblProjektZaliczeniowyPrzedmiotu);

        JLabel lblAutorzy = new JLabel("Autorzy");
        lblAutorzy.setHorizontalAlignment(SwingConstants.CENTER);
        lblAutorzy.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAutorzy.setBounds(43, 52, 257, 17);
        getContentPane().add(lblAutorzy);

        JLabel lblMarekGadzalski = new JLabel("Marek Gadzalski");
        lblMarekGadzalski.setFont(new Font("Vijaya", Font.PLAIN, 18));
        lblMarekGadzalski.setHorizontalAlignment(SwingConstants.CENTER);
        lblMarekGadzalski.setBounds(113, 76, 117, 17);
        getContentPane().add(lblMarekGadzalski);

        this.setBounds(200, 100, 350, 180);
        setVisible(true);
        setResizable(false);

    }

}

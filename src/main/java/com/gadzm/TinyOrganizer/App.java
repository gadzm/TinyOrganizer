package com.gadzm.TinyOrganizer;

import com.gadzm.TinyOrganizer.gui.window.MainWindow;
import java.awt.EventQueue;

public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                }
            }
        });
    }
}

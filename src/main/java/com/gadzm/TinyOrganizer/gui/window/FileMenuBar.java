package com.gadzm.TinyOrganizer.gui.window;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class FileMenuBar extends JMenu {

    private static final long serialVersionUID = 1L;
    private JMenuItem exitM;
    private JMenuItem save;
    private JMenuItem open;
    private JMenuItem saveIcal;

    FileMenuBar() {
        super("Plik");
        populateMenu();
    }

    public final void populateMenu() {
        exitM = new JMenuItem("Zakończ");
        save = new JMenuItem("Eksportuj XML");
        save.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        open = new JMenuItem("Importuj XML");
        open.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        saveIcal = new JMenuItem("Eksportuj iCalendar");
        this.add(open);
        this.add(save);
        this.add(saveIcal);
        this.add(exitM);

    }

    public void addCloseAdapter(ActionListener a) {
        this.exitM.addActionListener(a);
    }

    public void addSaveAdapter(ActionListener a) {
        this.save.addActionListener(a);
    }

    public void addOpenAdapter(ActionListener a) {
        this.open.addActionListener(a);
    }

    public void addSaveIcalAdapter(ActionListener a) {
        this.saveIcal.addActionListener(a);
    }

}

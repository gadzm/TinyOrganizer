package com.gadzm.TinyOrganizer;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;

class EventMenuBar extends JMenu {

    private static final long serialVersionUID = 1L;
    private JMenuItem addEventM;
    private JMenuItem filterEvents;
    private JMenuItem removeEvents;

    EventMenuBar() {
        super("Wydarzenia");
        populateMenu();
    }

    final public void populateMenu() {
        addEventM = new JMenuItem("Dodaj wydarzenie");
        addEventM.setAccelerator(KeyStroke.getKeyStroke('I', CTRL_DOWN_MASK));
        this.add(addEventM);
        
        filterEvents = new JMenuItem("Filtruj wydarzenia");
        filterEvents.setToolTipText("Filtruje wydarzenia wyświetlane w zbiorczej tabeli wydarzeń");
        ToolTipManager.sharedInstance().setInitialDelay(0);
        this.add(filterEvents);
        
        filterEvents.setAccelerator(KeyStroke.getKeyStroke('F', CTRL_DOWN_MASK));
        removeEvents = new JMenuItem("Usuń wydarzenia");
        this.add(removeEvents);
        
        removeEvents.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK));
    }

    public void addAddEventAction(ActionListener e) {
        this.addEventM.addActionListener(e);
    }

    public void addFilterEventAction(ActionListener e) {
        this.filterEvents.addActionListener(e);
    }

    public void addRemoveEventAction(ActionListener e) {
        this.removeEvents.addActionListener(e);
    }
}

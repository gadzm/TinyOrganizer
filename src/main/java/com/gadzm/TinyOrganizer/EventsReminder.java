package com.gadzm.TinyOrganizer;

import java.util.List;
import javax.swing.JFrame;

public class EventsReminder implements Runnable {

    private final JFrame parent;

    public EventsReminder(JFrame parent) {
        this.parent = parent;
    }

    public void run() {
        for (;;) {
            try {
                Thread.sleep(10000);
                checkForReminds();
            } catch (InterruptedException e) {

            }
        }
    }

    private void checkForReminds() {
        List<Event> tmp = EventContainer.GetInstance().checkForEventsToRemind();
        if (tmp.size() > 0) {
            for (Event e : tmp) {
                showDialog(e);
            }
        }
    }

    private void showDialog(Event e) {
        RemindDialog remindDialog = new RemindDialog(this.parent, e);
    }
}

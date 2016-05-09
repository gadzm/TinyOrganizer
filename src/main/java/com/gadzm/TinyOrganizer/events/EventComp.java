package com.gadzm.TinyOrganizer.events;

import java.util.Comparator;

public class EventComp implements Comparator<Event> {

    public int compare(Event o1, Event o2) {
        if (o1.getEventDate().after(o2.getEndDate()) || o1.getEventDate().equals(o2.getEndDate())) {
            return 1;
        }
        if (o1.getEndDate().before(o2.getEventDate()) || o1.getEndDate().equals(o2.getEventDate())) {
            return -1;
        }
        return 0;

    }

}

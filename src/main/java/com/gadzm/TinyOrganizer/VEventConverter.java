package com.gadzm.TinyOrganizer;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Trigger;

public class VEventConverter {

    private VEventConverter() {

    }

    static public VEvent eventToVEvent(Event event) {

        DateTime startDate = new DateTime(event.getEventDate().getTime());
        DateTime endDate = new DateTime(event.getEndDate().getTime());

        PropertyList propList = new PropertyList();
        propList.add(new DtStart(startDate));
        propList.add(new DtEnd(endDate));
        propList.add(new Summary(event.getTitle()));
        propList.add(new Location(event.getPlace()));
        propList.add(new Description(event.getContent()));
        if (event.isReminded()) {
            DateTime alarmDate = new DateTime(event.getReminderDate().getTime());
            propList.add(new Trigger(alarmDate));
        }
        VEvent vEvent = new VEvent(propList);
        return vEvent;
    }

}

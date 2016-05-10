package com.gadzm.TinyOrganizer.events;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {

    private final String title;
    private final String content;
    private final String place;
    private final Calendar startDate;
    private final Calendar endDate;
    private boolean reminder;
    private Calendar reminderDate;
    private final SimpleDateFormat dateFormat;

    static public Event getEventWithRemind(String title, String content, String place, Calendar startDate, Calendar endDate, Calendar remind) {
        return new Event(title, content, place, startDate, endDate, remind);
    }

    static public Event getEventWithoutRemind(String title, String content, String place, Calendar startDate, Calendar endDate) {
        return new Event(title, content, place, startDate, endDate);
    }

    static public Event getEventCopy(Event e) {
        return new Event(e);
    }

    private Event(String title, String content, String place, Calendar startDate, Calendar endDate) {
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
        this.startDate = new GregorianCalendar();
        this.startDate.setTime(startDate.getTime());
        this.endDate = new GregorianCalendar();
        this.endDate.setTime(endDate.getTime());
        this.title = title;
        this.content = content;
        this.place = place;
        this.reminder = false;
    }

    private Event(String title, String content, String place, Calendar startDate, Calendar endDate, Calendar remind) {
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
        this.startDate = new GregorianCalendar();
        this.startDate.setTime(startDate.getTime());
        this.endDate = new GregorianCalendar();
        this.endDate.setTime(endDate.getTime());
        this.title = title;
        this.content = content;
        this.place = place;
        this.reminder = true;
        this.reminderDate = new GregorianCalendar();
        this.reminderDate.setTime(remind.getTime());
    }

    private Event(Event e) {
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
        this.startDate = new GregorianCalendar();
        this.startDate.setTime(e.getEventDate().getTime());
        this.endDate = new GregorianCalendar();
        this.endDate.setTime(e.getEndDate().getTime());
        this.title = e.getTitle();
        this.content = e.getContent();
        this.place = e.getPlace();
        if (e.isReminded()) {
            this.reminder = true;
            this.reminderDate = new GregorianCalendar();
            this.reminderDate.setTime(e.getEventDate().getTime());
        } else {
            this.reminder = false;
        }
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPlace() {
        return place;
    }

    public Calendar getEventDate() {
        return startDate;
    }

    @Override
    public String toString() {
        String event;
        event = dateFormat.format(this.startDate.getTime()) + "\t" + this.title + "\t" + this.place + "\t" + this.content;
        return event;
    }

    public boolean isReminded() {
        return reminder;
    }

    public void removeReminder() {
        this.reminder = false;
    }

    public void setReminder(Calendar reminderDat) {
        setReminderDate(reminderDat);
        this.reminder = true;
    }

    public Calendar getReminderDate() {
        return reminderDate;
    }

    private void setReminderDate(Calendar reminderDate) {
        this.reminderDate = reminderDate;
    }

    public int compareToCalendar(Calendar cal) {
        if (this.startDate.after(cal)) {
            return 1;
        } else if (this.startDate.before(cal)) {
            return -1;
        } else {
            return 0;
        }
    }

}

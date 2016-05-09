package com.gadzm.TinyOrganizer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {

    private String title;
    private String content;
    private String place;
    private Calendar startDate;
    private Calendar endDate;
    private boolean reminder;
    private Calendar reminderDate;

    final private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");

    static public Event getEventWithRemind(String title, String content, String place, Calendar startDate, Calendar endDate, Calendar remind) {
        return new Event(title, content, place, startDate, endDate, remind);
    }

    static public Event getEventWithoutRemind(String title, String content, String place, Calendar startDate, Calendar endDate) {
        return new Event(title, content, place, startDate, endDate);
    }

    static public Event getEventCopy(Event e) {
        return new Event(e);
    }

    /*
	 * konstruktor bez przypomnienia
     */
    private Event(String title, String content, String place, Calendar startDate, Calendar endDate) {
        this.startDate = new GregorianCalendar();
        this.startDate.setTime(startDate.getTime());
        this.endDate = new GregorianCalendar();
        this.endDate.setTime(endDate.getTime());
        this.title = title;
        this.content = content;
        this.place = place;
        this.reminder = false;
    }

    /*
	 * konstruktor z przypomnieniem
     */
    private Event(String title, String content, String place, Calendar startDate, Calendar endDate, Calendar remind) {
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

    public Calendar getEndDate() {
        return endDate;
    }

    private Event(Event e) {
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
        event = Event.dateFormat.format(this.startDate.getTime()) + "\t" + this.title + "\t" + this.place + "\t" + this.content;
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

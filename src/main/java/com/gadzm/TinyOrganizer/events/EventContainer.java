package com.gadzm.TinyOrganizer.events;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;
import java.io.*;
import com.thoughtworks.xstream.XStream;

public class EventContainer {

    private static EventContainer instance;

    final private static TreeSet<Event> eventSet = new TreeSet<Event>(new EventComp());
    final private List<Event> temporaryContainer = new ArrayList<Event>();

    public void addEvent(String title, String content, String place, Calendar startDate, Calendar endDate) throws SameDateException {

        if (!eventSet.add(Event.getEventWithoutRemind(title, content, place, startDate, endDate))) {
            throw new SameDateException();
        }
    }

    public void addEvent(String title, String content, String place, Calendar startDate, Calendar endDate, Calendar remind) throws SameDateException {

        if (!eventSet.add(Event.getEventWithRemind(title, content, place, startDate, endDate, remind))) {
            throw new SameDateException();
        }
    }

    public int getSize() {
        return eventSet.size();
    }

    private EventContainer() {
    }

    public static EventContainer GetInstance() {
        if (instance == null) {
            instance = new EventContainer();
        }
        return instance;
    }

    public List<Event> getAllEvents() {
        synchronized (temporaryContainer) {
            temporaryContainer.clear();
            for (Event event : eventSet) {
                temporaryContainer.add(event);
            }
            return temporaryContainer;
        }
    }

    public List<Event> getEventsBefore(Calendar before) {
        temporaryContainer.clear();
        for (Event event : eventSet) {
            if (event.compareToCalendar(before) < 0) {
                temporaryContainer.add(event);
            }
        }
        return temporaryContainer;
    }

    public List<Event> getEventsAfter(Calendar after) {
        temporaryContainer.clear();
        for (Event event : eventSet) {
            if (event.compareToCalendar(after) > 0) {
                temporaryContainer.add(event);
            }
        }
        return temporaryContainer;
    }

    public List<Event> getEventsBetween(Calendar before, Calendar after) {
        temporaryContainer.clear();
        for (Event event : eventSet) {
            if (event.compareToCalendar(after) > 0 && event.compareToCalendar(before) < 0) {
                temporaryContainer.add(event);
            }
        }
        return temporaryContainer;
    }

    public List<Event> getEventsOnDay(Calendar date) {
        Calendar dayBegins = new GregorianCalendar();
        dayBegins.setTime(date.getTime());
        dayBegins.set(Calendar.HOUR_OF_DAY, 0);
        dayBegins.set(Calendar.MINUTE, 0);

        Calendar dayEnds = new GregorianCalendar();
        dayEnds.setTime(date.getTime());
        dayEnds.set(Calendar.HOUR_OF_DAY, 23);
        dayEnds.set(Calendar.MINUTE, 59);
        return getEventsBetween(dayEnds, dayBegins);
    }

    public void writeToXML(File file) throws IOException {
        XStream xstream = new XStream();
        FileWriter fileWriter;
        fileWriter = new FileWriter(file);
        ObjectOutputStream outputStream = xstream.createObjectOutputStream(fileWriter);
        for (Event event : eventSet) {
            outputStream.writeObject(event);
        }
        outputStream.flush();
        outputStream.close();
    }

    public void readFromXML(File file) throws IOException {
        XStream xstream = new XStream();
        FileReader fileReader;
        fileReader = new FileReader(file);
        ObjectInputStream inputStream = xstream.createObjectInputStream(fileReader);

        for (;;) {
            try {
                Event tmp = (Event) inputStream.readObject();
                eventSet.add(Event.getEventCopy(tmp));
            } catch (ClassNotFoundException e) {

            } catch (EOFException e) {
                break;
            }
        }
    }

    public void writeICalendar(File file) throws IOException {
        net.fortuna.ical4j.model.Calendar iCal = new net.fortuna.ical4j.model.Calendar();
        for (Event e : eventSet) {
            iCal.getComponents().add(VEventConverter.eventToVEvent(e));
        }
        FileWriter fW = new FileWriter(file);
        BufferedWriter bW = new BufferedWriter(fW);
        bW.write(iCal.toString());
        bW.flush();
        bW.close();
    }

    public boolean isDayWithEvent(Calendar date) {
        Calendar dayBegins = new GregorianCalendar();
        dayBegins.setTime(date.getTime());
        dayBegins.set(Calendar.HOUR_OF_DAY, 0);
        dayBegins.set(Calendar.MINUTE, 0);

        Calendar dayEnds = new GregorianCalendar();
        dayEnds.setTime(date.getTime());
        dayEnds.set(Calendar.HOUR_OF_DAY, 23);
        dayEnds.set(Calendar.MINUTE, 59);

        for (Event event : eventSet) {
            if (event.getEventDate().compareTo(dayBegins) > 0 && event.getEventDate().compareTo(dayEnds) < 0) {
                return true;
            }
        }
        return false;
    }

    public void removeEventsBefore(Calendar before) {
        List<Event> tmp = new ArrayList<Event>();
        for (Event event : eventSet) {
            if (event.compareToCalendar(before) < 0) {
                tmp.add(event);
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            eventSet.remove(tmp.get(i));
        }
    }

    public void removeEventsAfter(Calendar after) {
        List<Event> tmp = new ArrayList<Event>();
        for (Event event : eventSet) {
            if (event.compareToCalendar(after) > 0) {
                tmp.add(event);
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            eventSet.remove(tmp.get(i));
        }
    }

    public void removeEventsBetween(Calendar after, Calendar before) {
        List<Event> tmp = new ArrayList<Event>();
        for (Event event : eventSet) {
            if (event.compareToCalendar(after) > 0 && event.compareToCalendar(before) < 0) {
                tmp.add(event);
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            eventSet.remove(tmp.get(i));
        }
    }

    public List<Event> checkForEventsToRemind() {
        synchronized (temporaryContainer) {
            temporaryContainer.clear();
            Calendar currentDate = Calendar.getInstance();
            for (Event e : eventSet) {
                if (e.isReminded()) {
                    if (e.getReminderDate().before(currentDate) && e.getEventDate().after(currentDate)) {
                        temporaryContainer.add(e);
                        e.removeReminder();
                    }
                }
            }
            return temporaryContainer;
        }
    }

    private class SameDateException extends Exception {
    }
}

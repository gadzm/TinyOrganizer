package com.gadzm.TinyOrganizer.gui.dialogs;

import com.gadzm.TinyOrganizer.events.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

public class EventBox extends JLabel {

    private EventDetailBox detailBox;
    private final Event event;
    private int x, y, height, width, hourStart, hourEnd, minuteStart, minuteEnd;
    private final int tableHeight, tableWidth;
    private final SimpleDateFormat startFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private final SimpleDateFormat endFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public EventBox(Event event, int tableHeight, int tableWidth, EventDetailBox detailBox) {
        this.detailBox = detailBox;
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.event = event;
        prepareDialog();
    }

    private void prepareDialog() {
        calculateTime();
        calculatePosition();
        calculateDimensions();
        setUpBtn();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showEventDetail();
            }
        });
    }

    private void calculateTime() {
        Calendar calStart = event.getEventDate();
        hourStart = calStart.get(Calendar.HOUR_OF_DAY);
        minuteStart = calStart.get(Calendar.MINUTE);
        Calendar calEnd = event.getEndDate();
        hourEnd = calEnd.get(Calendar.HOUR_OF_DAY);
        minuteEnd = calEnd.get(Calendar.MINUTE);
    }

    private void calculatePosition() {
        double tmpX = (this.hourStart * 60 + this.minuteStart) / (24 * 60.0);
        tmpX *= this.tableHeight;
        this.y = (int) tmpX;

        this.x = (int) (this.tableWidth * 0.1);
    }

    private void calculateDimensions() {
        this.width = this.tableWidth - this.x;
        double tmpX = (this.hourEnd * 60 + this.minuteEnd) / (24 * 60.0);
        tmpX *= this.tableHeight;
        this.height = (int) tmpX - this.y;
    }

    private void setUpBtn() {
        setBounds(this.x, this.y, this.width, this.height);
        setOpaque(true);
        setBackground(new Color(60, 60, 60));
        setForeground(Color.orange);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        setUpTitle();
    }

    private void setUpTitle() {
        String name = "";
        name += this.event.getTitle() + ": ";
        name += startFormat.format(this.event.getEventDate().getTime()) + " - ";
        name += endFormat.format(this.event.getEndDate().getTime());
        setText(name);
        setFont(new Font("Arial", Font.BOLD, 13));

    }

    private void showEventDetail() {
        detailBox.fillData(this.event);
    }
}

package com.gadzm.TinyOrganizer.gui.window;

import com.gadzm.TinyOrganizer.calendar.CalendarController;
import com.gadzm.TinyOrganizer.calendar.CalendarTable;
import com.gadzm.TinyOrganizer.calendar.DailyTable;
import com.gadzm.TinyOrganizer.events.EventsReminder;
import com.gadzm.TinyOrganizer.calendar.EventsTableModel;
import com.gadzm.TinyOrganizer.calendar.MonthToDisplay;
import com.gadzm.TinyOrganizer.gui.dialogs.EventDetailBox;
import com.gadzm.TinyOrganizer.gui.dialogs.EventBox;
import com.gadzm.TinyOrganizer.gui.dialogs.RemoveEventsDialog;
import com.gadzm.TinyOrganizer.gui.dialogs.AddEventDialog;
import com.gadzm.TinyOrganizer.gui.dialogs.AboutDialog;
import com.gadzm.TinyOrganizer.events.Event;
import com.gadzm.TinyOrganizer.events.EventContainer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

import org.apache.commons.lang.WordUtils;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private final List<EventBox> eventOnDay = new ArrayList<EventBox>();
    private final static MonthToDisplay currentMonth = new MonthToDisplay();
    private final SimpleDateFormat calendarDateFormat = new SimpleDateFormat("MMM yyyy");
    private JPanel panelCalendar,
            panelEventTable;
    private JScrollPane eventScrollPane,
            calendarScrollPane;
    private JTabbedPane tabbedPane;
    private JLayeredPane panelDay;
    private JTable eventsTable;
    private DailyTable tableDayEvent;
    private CalendarTable calendarTable;
    private Calendar selecetedDate;
    private JMenuBar menuBar;
    private JLabel labelCurrentMonth;
    private JButton btnNextMonth,
            btnPreviousMonth,
            btnNextYear,
            btnPreviousYear,
            btnRemoveFilter,
            btnAddEventAt;

    public MainWindow() {
        initialize();
    }

    private void initialize() {
        initLookAndFeel();
        JFrame jFrame = new JFrame();
        setTitle("Kalendarz");
        setBounds(400, 25, 1025, 847);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        createMenuBar();

        createPanels();

        createCalendarPanel();

        createCalendar();
        createCalendarNavigation();

        createDayEventsPane();

        createEventsPanel();

        createEventsTable();

        JPopupMenu eventTablePopUp = new JPopupMenu();

        panelEventTable.add(eventTablePopUp);
        JMenuItem removeEvent = new JMenuItem("Usuń zdarzenie");
        eventTablePopUp.add(removeEvent);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new EventsReminder(this));
    }

    private void createPanels() {
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 1019, 797);
        getContentPane().add(tabbedPane);
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 1) {
//                    EventContainer.showAllEvents();
                }
            }
        });
    }

    private void createMenuBar() {
        class CloseAction implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                CloseFrame();
            }
        };
        class SaveAction implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                showSaveDialog();
            }
        };
        class SaveIcalAction implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                showSaveIcalDialog();
            }
        };
        class OpenAction implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                showOpenDialog();
            }
        };
        class AddAction implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                showdialogAddEvent();
            }
        };
        class RemoveAction implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                showRemoveEvent();
            }
        };

        FileMenuBar fileBar = new FileMenuBar();
        fileBar.addCloseAdapter(new CloseAction());
        fileBar.addSaveAdapter(new SaveAction());
        fileBar.addOpenAdapter(new OpenAction());
        fileBar.addSaveIcalAdapter(new SaveIcalAction());
        EventMenuBar eventBar = new EventMenuBar();
        eventBar.addAddEventAction(new AddAction());
        eventBar.addRemoveEventAction(new RemoveAction());

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileBar);
        menuBar.add(eventBar);
//        EventContainer.showAllEvents();
        /*
		 * o programie
         */
        JMenu about = new JMenu("O programie");
        JMenuItem aboutI = new JMenuItem("O programie");
        about.add(aboutI);
        aboutI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAboutDialog();
            }
        });
        menuBar.add(about);
    }

    /*
	 * aktualizacja tablicy zdarzen
     */
    private void updateTable() {
        this.eventsTable.setModel(new EventsTableModel(EventContainer.GetInstance().getAllEvents()));
    }

    private void CloseFrame() {
        super.dispose();
    }

    private void showAllEvents() {
        this.eventsTable.setModel(new EventsTableModel(EventContainer.GetInstance().getAllEvents()));
    }

    /*
	 * okno dialogowe dodawania zdarzeń
     */
    private void showdialogAddEvent() {
        AddEventDialog dialog = new AddEventDialog(this);
        dialog.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                updateTable();
            }
        });
    }

    private void showdialogAddEvent(Calendar date) {
        AddEventDialog dialog = new AddEventDialog(this, date);
        dialog.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                updateTable();
            }
        });
    }

    private void showRemoveEvent() {
        RemoveEventsDialog dialog = new RemoveEventsDialog(this);
        dialog.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                showAllEvents();
                updateTable();
            }
        });
    }

    private void showAboutDialog() {
        AboutDialog about = new AboutDialog(this);
        about.setVisible(true);
    }

    private void showCalendarDate() {
        this.labelCurrentMonth.setText(WordUtils.capitalizeFully(this.calendarDateFormat.format((currentMonth.getDate(1)).getTime())));
    }

    private void showSaveDialog() {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                EventContainer.GetInstance().writeToXML(fc.getSelectedFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(getContentPane(), "Proces zapisu do pliku zakonczył się niepowodzeniem", "Błąd zapisu do pliku", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showOpenDialog() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                EventContainer.GetInstance().readFromXML(fc.getSelectedFile());

            } catch (IOException e) {
                JOptionPane.showMessageDialog(getContentPane(), "Proces odczytu z pliku zakonczył się niepowodzeniem", "Błąd odczytu z pliku", JOptionPane.ERROR_MESSAGE);
            }
        }
        showAllEvents();
    }

    private void showSaveIcalDialog() {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                EventContainer.GetInstance().writeICalendar(fc.getSelectedFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(getContentPane(), "Proces odczytu z pliku zakonczył się niepowodzeniem", "Błąd odczytu z pliku", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void createDayEventsPane() {
        panelDay = new JLayeredPane();
        panelDay.setBackground(new Color(235, 235, 235));
        panelDay.setBounds(425, 10, 580, 721);
        panelDay.setLayout(null);
        panelCalendar.add(panelDay);
        tableDayEvent = new DailyTable(panelDay);
        panelDay.add(tableDayEvent, 1);
        panelCalendar.add(EventDetailBox.getEventDetailBox(25, 350));
    }

    private void showEventsOnDay(int selecetedCell) {
        clearEventBoxes();
        this.selecetedDate = currentMonth.getDate(selecetedCell);
        if (EventContainer.GetInstance().isDayWithEvent(selecetedDate)) {
            createEventBoxes();
        }
    }

    private void createEventBoxes() {
        List<Event> tmp = EventContainer.GetInstance().getEventsOnDay(selecetedDate);
        for (int i = 0; i < tmp.size(); i++) {
            eventOnDay.add(new EventBox(tmp.get(i), tableDayEvent.getHeight(), tableDayEvent.getWidth()));
        }
        putEventBoxes();
    }

    private void putEventBoxes() {
        for (int i = 0; i < this.eventOnDay.size(); i++) {
            this.panelDay.add(eventOnDay.get(i), 0);
        }
        panelDay.revalidate();
    }

    private void clearEventBoxes() {
        for (int i = 0; i < this.eventOnDay.size(); i++) {
            this.panelDay.remove(eventOnDay.get(i));
        }
        eventOnDay.clear();
        panelDay.revalidate();
        repaint();
    }

    private void createCalendarPanel() {
        panelCalendar = new JPanel();
        tabbedPane.addTab("Kalendarz", null, panelCalendar, null);
        panelCalendar.setLayout(null);
        calendarScrollPane = new JScrollPane();
        calendarScrollPane.setBounds(29, 52, 350, 207);
        panelCalendar.add(calendarScrollPane);
    }

    private void createCalendar() {
        calendarTable = new CalendarTable(this.currentMonth);
        
        calendarScrollPane.setViewportView(calendarTable);
        calendarTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = calendarTable.rowAtPoint(e.getPoint());
                int column = calendarTable.columnAtPoint(e.getPoint());
                btnAddEventAt.setEnabled(false);
                EventDetailBox.hideContent();
                try {
                    int selectedCell = (Integer) calendarTable.getValueAt(row, column);
                    showEventsOnDay(selectedCell);
                    btnAddEventAt.setEnabled(true);
                } catch (NullPointerException npe) {
                }
            }
        });
    }

    private void createCalendarNavigation() {
        final CalendarController calContr = new CalendarController(this.calendarTable, this.currentMonth);

        btnNextYear = new JButton(">>");
        btnNextYear.setBounds(330, 18, 49, 23);
        panelCalendar.add(btnNextYear);

        btnNextMonth = new JButton(">");
        btnNextMonth.setBounds(271, 18, 49, 23);
        panelCalendar.add(btnNextMonth);

        btnPreviousMonth = new JButton("<");
        btnPreviousMonth.setBounds(89, 18, 49, 23);
        panelCalendar.add(btnPreviousMonth);

        btnPreviousYear = new JButton("<<");
        btnPreviousYear.setBounds(30, 18, 49, 23);
        panelCalendar.add(btnPreviousYear);

        btnNextMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calContr.neighbourMonth(true);
                showCalendarDate();
            }
        });
        btnPreviousMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calContr.neighbourMonth(false);
                showCalendarDate();
            }
        });
        btnNextYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calContr.neighbourYear(true);
                showCalendarDate();
            }
        });
        btnPreviousYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calContr.neighbourYear(false);
                showCalendarDate();
            }
        });

        labelCurrentMonth = new JLabel();
        labelCurrentMonth.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelCurrentMonth.setHorizontalAlignment(SwingConstants.CENTER);
        labelCurrentMonth.setBounds(151, 11, 110, 36);
        panelCalendar.add(labelCurrentMonth);
        showCalendarDate();

        btnAddEventAt = new JButton("Dodaj wydarzenie");
        btnAddEventAt.setEnabled(false);
        btnAddEventAt.setBounds(139, 270, 131, 30);
        panelCalendar.add(btnAddEventAt);

        btnAddEventAt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showdialogAddEvent(selecetedDate);
            }
        });
    }

    private void createEventsPanel() {
        panelEventTable = new JPanel();
        tabbedPane.addTab("Wydarzenia", null, panelEventTable, null);
        panelEventTable.setLayout(null);
        eventScrollPane = new JScrollPane();
        eventScrollPane.setBounds(24, 39, 842, 612);
    }

    private void createEventsTable() {
        panelEventTable.add(eventScrollPane);
        eventsTable = new JTable(new EventsTableModel(EventContainer.GetInstance().getAllEvents()));
        eventScrollPane.setViewportView(eventsTable);
        eventsTable.setBorder(new LineBorder(new Color(0, 0, 0)));
        eventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventsTable.setShowGrid(true);
        btnRemoveFilter = new JButton("Pokarz wszystkie wydarzenia");
        btnRemoveFilter.setBounds(347, 681, 196, 39);
        panelEventTable.add(btnRemoveFilter);
        btnRemoveFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
    }

    private static void initLookAndFeel() {
        String lookAndFeel = null;
        lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
        } catch (UnsupportedLookAndFeelException e) {
        } catch (Exception e) {
        }
    }
    
    public static MonthToDisplay getCurrentMonth(){
        return currentMonth;
    }
}

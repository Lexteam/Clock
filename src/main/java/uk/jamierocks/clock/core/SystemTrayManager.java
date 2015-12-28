package uk.jamierocks.clock.core;

import uk.jamierocks.clock.Main;
import uk.jamierocks.clock.core.event.tray.TrayEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SystemTrayManager {
    private final SystemTray systemTray;
    private final TrayIcon trayIcon;

    public SystemTrayManager(){
        systemTray = SystemTray.getSystemTray();
        trayIcon = new TrayIcon(createImage("icon.png", "tray icon"));

        PopupMenu menu = new PopupMenu();

        MenuItem alarmsItem = new MenuItem("Alarms");
        MenuItem visibilityItem = new MenuItem("ToggleVisibility");
        MenuItem quitItem = new MenuItem("Quit");

        menu.add(alarmsItem);
        menu.addSeparator();
        menu.add(visibilityItem);
        menu.addSeparator();
        menu.add(quitItem);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem)e.getSource();
                if (item.getLabel().equals("Alarms")) {
                    Main.getInstance().getEventbus().post(new TrayEvent(TrayEvent.TrayEventType.ALARM_OVERVIEW));
                }
                else if (item.getLabel().equals("ToggleVisibility")) {
                    Main.getInstance().getEventbus().post(new TrayEvent(TrayEvent.TrayEventType.TOGGLE_VISABLIITY));
                }
                else if (item.getLabel().equals("Quit")) {
                    Main.getInstance().getEventbus().post(new TrayEvent(TrayEvent.TrayEventType.CLOSE));
                }
            }
        };

        alarmsItem.addActionListener(listener);
        visibilityItem.addActionListener(listener);
        quitItem.addActionListener(listener);

        trayIcon.setPopupMenu(menu);
        trayIcon.setToolTip("Clock");
    }

    public void hideIcon() {
        systemTray.remove(trayIcon);
    }

    public void showIcon() throws AWTException {
        systemTray.add(trayIcon);
    }

    protected static Image createImage(String path, String description) {
        URL pathURL = Main.class.getClassLoader().getResource(path);
        if (pathURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(pathURL, description)).getImage();
        }
    }
}

package uk.jamierocks.clock.core;

import uk.jamierocks.clock.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SystemTrayManager {
    private SystemTray systemTray;
    private TrayIcon trayIcon;

    public SystemTrayManager(){
        systemTray = SystemTray.getSystemTray();
        trayIcon = new TrayIcon(createImage("icon.png", "tray icon"));
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

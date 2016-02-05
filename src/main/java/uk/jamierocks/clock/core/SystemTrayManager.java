/*
 * This file is part of Clock, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015, Jamie Mansfield <https://github.com/jamierocks>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.jamierocks.clock.core;

import uk.jamierocks.clock.Main;
import uk.jamierocks.clock.core.event.tray.TrayEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SystemTrayManager {
    private final SystemTray systemTray;
    private final TrayIcon trayIcon;

    public SystemTrayManager(){
        systemTray = SystemTray.getSystemTray();
        trayIcon = createSizedTrayIcon("src/main/resources/icon.png", 16, 16);

        PopupMenu menu = new PopupMenu();

        MenuItem alarmsItem = new MenuItem("Alarm Overview");
        MenuItem visibilityItem = new MenuItem("Toggle Visibility");
        MenuItem quitItem = new MenuItem("Quit");

        menu.add(alarmsItem);
        menu.addSeparator();
        menu.add(visibilityItem);
        menu.addSeparator();
        menu.add(quitItem);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem)e.getSource();
                if (item.getLabel().equals("Alarm Overview")) {
                    Main.getInstance().getEventbus().post(new TrayEvent(TrayEvent.TrayEventType.TOGGLE_ALARM_OVERVIEW_PANEL));
                }
                else if (item.getLabel().equals("Toggle Visibility")) {
                    Main.getInstance().getEventbus().post(new TrayEvent(TrayEvent.TrayEventType.TOGGLE_CLOCK_PANEL));
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

    private static TrayIcon createSizedTrayIcon(String file, int width, int height){
        Image img = new ImageIcon(file).getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, width, height, null);
        return new TrayIcon(new ImageIcon(bi).getImage());
    }
}

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
package uk.jamierocks.clock.core.event.tray;

import uk.jamierocks.clock.core.event.Event;

/**
 * Represents a tray event.
 */
public class TrayEvent implements Event {

    private final TrayEventType type;

    public TrayEvent(TrayEventType type) {
        this.type = type;
    }

    /**
     * Gets the type of the event.
     *
     * @return The {@link TrayEventType}.
     */
    public TrayEventType getType() {
        return this.type;
    }

    /**
     * Represents the various buttons that may be clicked.
     */
    public enum TrayEventType {

        TOGGLE_CLOCK_PANEL,
        TOGGLE_ALARM_OVERVIEW_PANEL,
        CLOSE
    }
}

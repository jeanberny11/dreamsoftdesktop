package com.dreamsoft.mdiwindows;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.image.ImageView;

public class DsMDIEvent extends Event {
    private static final long serialVersionUID = 2249682426993045124L;
    public static final EventType<DsMDIEvent> EVENT_CLOSED;
    public static final EventType<DsMDIEvent> EVENT_MINIMIZED;
    public ImageView logo;

    public DsMDIEvent(ImageView logoImage, EventType<? extends Event> eventType) {
        super(eventType);
        this.logo = logoImage;
    }

    public DsMDIEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    static {
        EVENT_CLOSED = new EventType<>(ANY, "EVENT_CLOSED");
        EVENT_MINIMIZED = new EventType<>(ANY, "EVENT_MINIMIZED");
    }
}

package xyz.sethy.event;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public abstract class EventAPI
{
    private static Framework framework;

    private EventAPI() throws IllegalAccessException
    {
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Set the EventFramework, this should be called before the application is run.
     * @param newFramework
     */
    public static synchronized void setFramework(final Framework newFramework)
    {
        framework = newFramework;
    }

    /**
     * Calls a new Event
     * @param event
     * @see Event
     * @return
     */
    public static synchronized AtomicBoolean callEvent(final Event event)
    {
        try
        {
            return framework.callEvent(event);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return new AtomicBoolean(false);
    }

    public static synchronized Logger getLogger()
    {
        return framework.getLogger();
    }
}

package xyz.sethy.event;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class EventAPI
{
    private static Framework framework;

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
}

package xyz.sethy.event;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public interface Framework
{
    /**
     * Call a new event, will throw InterruptedException if the event is interrupted.
     * @param event
     * @return if the event was successfully called.
     * @throws InterruptedException
     * @see Event
     * @see InterruptedException
     */
    AtomicBoolean callEvent(Event event) throws InterruptedException;

    /**
     * the max amount of threads that this system will use.
     * WARNING: Do not set to more than your CPU has threads, such as the Xeon E5-2620 has 44 threads, you make sure the max threads is less than 44.
     * @return the max amount of threads that this system will use.
     */
    AtomicInteger getMaxThreads();

    Logger getLogger();
}

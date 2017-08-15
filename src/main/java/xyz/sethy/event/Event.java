package xyz.sethy.event;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Event
{
    private final AtomicBoolean cancelled;
    private final AtomicLong delay;
    private final AtomicBoolean executed;

    public Event()
    {
        this.cancelled = new AtomicBoolean(false);
        this.delay = new AtomicLong(0L);
        this.executed = new AtomicBoolean(false);
    }

    public abstract void onExecution();

    public AtomicBoolean getCancelled()
    {
        return cancelled;
    }

    public Event withDelay(Long time)
    {
        this.delay.set(time);
        return this;
    }

    public AtomicLong getDelay()
    {
        return delay;
    }

    public AtomicBoolean getExecuted()
    {
        return executed;
    }
}

package xyz.sethy.event;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Event
{
    private final AtomicBoolean cancelled;
    private final AtomicLong delay;
    private final AtomicBoolean executed;
    private final AtomicInteger priority;

    public Event()
    {
        this.cancelled = new AtomicBoolean(false);
        this.delay = new AtomicLong(0L);
        this.executed = new AtomicBoolean(false);
        this.priority = new AtomicInteger(0);
    }

    public abstract void onExecution();

    public AtomicBoolean getCancelled()
    {
        return cancelled;
    }

    public Event withPriority(Integer priority)
    {
        this.priority.set(priority);
        return this;
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

    public AtomicInteger getPriority()
    {
        return priority;
    }
}

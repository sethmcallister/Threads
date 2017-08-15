package xyz.sethy.event.impl;

import xyz.sethy.event.Event;
import xyz.sethy.event.Framework;
import xyz.sethy.event.comparator.IntegerComparator;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EventFramework implements Framework
{
    private final AtomicInteger maxThreads;
    private final Queue<Event> events;

    public EventFramework()
    {
        this.maxThreads = new AtomicInteger(2);
        this.events = new PriorityQueue<>(new IntegerComparator());

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new EventTask(), 1, 20, TimeUnit.MILLISECONDS);
    }

    public AtomicBoolean callEvent(final Event event)
    {
        if(event.getDelay().get() > 0L)
        {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
            service.scheduleWithFixedDelay(new EventCompleter(event), event.getDelay().get(), Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            try
            {
                service.awaitTermination(3000, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return new AtomicBoolean(false);
            }
            return new AtomicBoolean(true);
        }
        return new AtomicBoolean(this.events.add(event));
    }

    public AtomicInteger getMaxThreads()
    {
        return maxThreads;
    }

    public class EventTask implements Runnable
    {

        @Override
        public void run()
        {
            int i = 0;
            while(!events.isEmpty() && i <= getMaxThreads().get())
            {
                Event event = events.poll();
                if(event.getCancelled().get())
                    continue;

                if(event.getExecuted().get())
                    continue;

                new EventCompleter(event).run();
                i++;
            }
        }
    }

    public class EventCompleter extends Thread
    {
        private final Event event;

        EventCompleter(final Event event)
        {
            this.event = event;
        }

        @Override
        public void run()
        {
            event.onExecution();
            event.getExecuted().set(true);
        }
    }
}

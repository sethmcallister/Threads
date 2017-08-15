package xyz.sethy.event.comparator;

import xyz.sethy.event.Event;

import java.util.Comparator;
import java.util.Objects;

public class IntegerComparator implements Comparator<Event>
{
    @Override
    public int compare(final Event o1, final Event o2)
    {
        if(o1.getPriority().get() > o2.getPriority().get())
            return -1;
        else if(o1.getPriority().get() < o2.getPriority().get())
            return 1;
        else if(Objects.equals(o1.getPriority().get(), o2.getPriority().get()))
            return 0;
        else
            return 0;
    }
}

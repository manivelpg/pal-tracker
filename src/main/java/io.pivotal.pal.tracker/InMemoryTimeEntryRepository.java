package io.pivotal.pal.tracker;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private  HashMap<Long,TimeEntry> database = new  HashMap<Long,TimeEntry>();

    private Long idIncrementer=0L;

    public TimeEntry create(TimeEntry timeEntry) {
        idIncrementer = idIncrementer+1L;
        timeEntry.setId(idIncrementer);
        database.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return database.get(id);
    }

    public List<TimeEntry> list() {

        List<TimeEntry> timeEntryList =new ArrayList(database.values());

        return timeEntryList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(database.get(id) != null){
            timeEntry.setId(id);
            database.put(id,timeEntry);
            return timeEntry;
        }
        return null;
    }

    public void delete(long id) {
        database.remove(id);
    }
}

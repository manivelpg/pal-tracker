package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }

   @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        timeEntryToCreate=timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(timeEntryToCreate, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if( timeEntry!=null){
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>>  listResponseEntity = null;
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        if(timeEntryList != null){
            return new ResponseEntity(timeEntryList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(timeEntryList, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {
        expected=timeEntryRepository.update(timeEntryId,expected);
        return expected !=null ? new ResponseEntity(expected, HttpStatus.OK) : new ResponseEntity(expected, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

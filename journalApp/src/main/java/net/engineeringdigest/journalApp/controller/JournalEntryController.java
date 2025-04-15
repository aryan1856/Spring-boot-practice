package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/journals")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public boolean createEntry(@RequestBody Journal journal){
        journalEntryService.saveEntry(journal);
        return true;
    }

    @GetMapping
    public ResponseEntity<?> showEntries(){
        return journalEntryService.showEntries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        ObjectId objId = new ObjectId(id);
        return journalEntryService.findById(objId);
    }

    @PutMapping("/update")
    public ResponseEntity<?> findByIdAndUpdate(@RequestBody Journal journal){
        return journalEntryService.findByIdAndUpdate(journal);
    }

}

package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
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

    @Autowired
    private UserService userService;

    @PostMapping("{username}")
    public boolean createEntry(@RequestBody Journal journal,
                               @PathVariable String username){
        journalEntryService.saveEntry(journal, username);
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

    @GetMapping("/u/{username}")
    public ResponseEntity<?> getAllByUsername(@PathVariable String username){
        return journalEntryService.findByUsername(username);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> findByIdAndDelete(@PathVariable ObjectId id,
                                               @PathVariable String username){
        return journalEntryService.findByIdAndDelete(id, username);
    }

}

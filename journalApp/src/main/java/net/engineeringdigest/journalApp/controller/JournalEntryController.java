package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/journals")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public boolean createEntry(@RequestBody Journal journal){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        journalEntryService.saveEntry(journal, name);
        return true;
    }

//    @GetMapping
//    public ResponseEntity<?> showEntries(){
//        return journalEntryService.showEntries();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        ObjectId objId = new ObjectId(id);
        return journalEntryService.findById(objId);
    }

    @PutMapping("/update")
    public ResponseEntity<?> findByIdAndUpdate(@RequestBody Journal journal){
        return journalEntryService.findByIdAndUpdate(journal);
    }

    @GetMapping
    public ResponseEntity<?> getAllByUsername(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return journalEntryService.findByUsername(name);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> findByIdAndDelete(@PathVariable ObjectId id,
                                               @PathVariable String username){
        return journalEntryService.findByIdAndDelete(id, username);
    }

}

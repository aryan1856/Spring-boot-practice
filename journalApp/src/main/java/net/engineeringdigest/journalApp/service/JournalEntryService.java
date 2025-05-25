package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public ResponseEntity<?> saveEntry(Journal journal, String username){
        try{
            User user = userService.findByUsername(username);
            if(user==null)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                                .body("user not found");
            Journal saved = journalEntryRepository.save(journal);
            user.getJournalList().add(saved);
            userService.save(user);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> showEntries() {
        try{
            List<Journal> entries = journalEntryRepository.findAll();
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findById(ObjectId id){
        try {
            Journal entry = journalEntryRepository.findById(id).orElseThrow(()->new RuntimeException("Journal not found"));
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findByIdAndUpdate(Journal journal) {
        try {
            Journal find = journalEntryRepository.findById(journal.getId()).orElse(null);
            if(find!=null) {
                find.setTitle(
                        journal.getTitle() != null &&
                                !journal.getTitle().isEmpty() ?
                                journal.getTitle() : find.getTitle()
                );

                find.setContent(
                        journal.getContent() != null &&
                                !journal.getContent().isEmpty() ?
                                journal.getContent() : find.getContent()
                );
                journalEntryRepository.save(find);
                return new ResponseEntity<>(find, HttpStatus.OK);
            }else
                throw new RuntimeException("Journal not found");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findByUsername(String username) {
        try {
            User user = userService.findByUsername(username);
            if(user==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            List<Journal> journalList = user.getJournalList();
            return ResponseEntity.status(HttpStatus.OK).body(journalList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @Transactional
    public ResponseEntity<?> findByIdAndDelete(ObjectId id, String username) {
        User user = userService.findByUsername(username);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        user.getJournalList().removeIf(a -> a.getId().equals(id));
        userService.save(user);
        journalEntryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Journal deleted");
    }
}

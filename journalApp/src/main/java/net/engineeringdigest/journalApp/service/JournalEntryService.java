package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public ResponseEntity<?> saveEntry(Journal journal){
        try{
            journalEntryRepository.save(journal);
            return new ResponseEntity<>(journal, HttpStatus.OK);
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
}

package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(Journal journal){
        journalEntryRepository.save(journal);
    }

    public List<Journal> showEntries(){
        return journalEntryRepository.findAll();
    }

    public Journal findById(ObjectId id){
        return journalEntryRepository.findById(id).orElseThrow(()->new RuntimeException("Journal not found"));
    }

    public Journal findByIdAndUpdate(Journal journal) {
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
        }
        return find;
    }
}

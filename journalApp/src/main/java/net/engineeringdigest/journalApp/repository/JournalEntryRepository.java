package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<Journal, ObjectId> {

}
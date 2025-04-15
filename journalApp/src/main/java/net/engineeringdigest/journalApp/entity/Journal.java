package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "journal_entries")
@Getter
@Setter
public class Journal {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}

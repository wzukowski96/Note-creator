package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @GeneratedValue
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean deleted = false;
}

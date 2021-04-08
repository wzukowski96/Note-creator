package wz.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Note extends Auditable{

    @GeneratedValue
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String title;
    private String content;
    private int version = 1;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

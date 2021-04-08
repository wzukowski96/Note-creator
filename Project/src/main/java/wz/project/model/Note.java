package wz.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note extends Auditable{

    @GeneratedValue
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String title;
    private String content;
    private boolean deleted = false;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

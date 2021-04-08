package wz.project.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import wz.project.model.Auditable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO extends Auditable {

    private UUID id;
    private String title;
    private String content;
    private int version;

    public NoteDTO(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDTO(UUID id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

package wz.project.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import wz.project.model.Auditable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO extends Auditable {

    private UUID id;
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String content;
    private boolean deleted;

    public NoteDTO(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDTO(UUID id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

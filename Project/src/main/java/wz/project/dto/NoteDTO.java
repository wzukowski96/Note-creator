package wz.project.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import wz.project.model.Auditable;

import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class NoteDTO{

    private UUID id;
    private String title;
    private String content;
    private int version = 1;
    private boolean deleted;
    @CreatedDate
    private OffsetDateTime created;
    @LastModifiedDate
    private OffsetDateTime modified;

    public OffsetDateTime getCreated() {
        return created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public NoteDTO(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDTO(String content) {
        this.content = content;
    }

    public NoteDTO(UUID id, String content) {
        this.id = id;
        this.content = content;
    }

    public NoteDTO(UUID id, String title, String content, int version) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.version = version;
    }

    public NoteDTO(UUID id, String title, String content, int version, OffsetDateTime created, OffsetDateTime modified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.version = version;
        this.created = created;
        this.modified = modified;
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

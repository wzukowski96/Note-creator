package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private UUID id;
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean deleted = false;

    public NoteDTO(UUID id, @NotBlank @NotNull String title, @NotBlank @NotNull String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
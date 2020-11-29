package yte.intern.project.application.usecases.ManageEvents.DTO.Chat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewChatDto {
    private String question;

    private String answer;
}

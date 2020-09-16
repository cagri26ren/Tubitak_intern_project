package yte.intern.project.application.usecases.ManageEvents.DTO.Chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.common.Validation.TcKimlikNo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddChatDto {
    @TcKimlikNo
    private String tcKimlikNo;

    private String question;

    private String answer;
}

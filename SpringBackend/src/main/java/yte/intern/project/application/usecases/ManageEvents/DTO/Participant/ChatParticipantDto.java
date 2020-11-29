package yte.intern.project.application.usecases.ManageEvents.DTO.Participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.ManageEvents.DTO.Chat.ViewChatDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AnswerDto;
import yte.intern.project.application.usecases.common.Validation.TcKimlikNo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatParticipantDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    private String email;

    @TcKimlikNo
    private String tcKimlikNo;

    private LocalDate joinDate;

    private LocalTime joinTime;

    private Long age;

    private String gender;

    private List<ViewChatDto> chats;

}
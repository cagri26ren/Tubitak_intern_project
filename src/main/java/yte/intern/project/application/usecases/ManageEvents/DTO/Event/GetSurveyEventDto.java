package yte.intern.project.application.usecases.ManageEvents.DTO.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.ChatParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.SurveyParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetSurveyEventDto {

    @NotBlank
    private String name;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalDate endDate;

    private LocalTime endTime;

    private List<SurveyParticipantDto> participants;
}
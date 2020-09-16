package yte.intern.project.application.usecases.ManageEvents.DTO.Event;

import lombok.*;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AddSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.PostSurveyDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.QuestionDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddEventDto {

    @NotBlank
    private String name;

    @Min( value = 0, message = "Course capacity can not be 0")
    private Long max;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalDate endDate;

    private LocalTime endTime;

    private Double lat;

    private Double lng;

    private boolean askAge;

    private boolean askGender;

    private List<QuestionDto> questions;

    private List<PostSurveyDto> surveyQuestions;
}

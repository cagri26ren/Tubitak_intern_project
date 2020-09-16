package yte.intern.project.application.usecases.ManageEvents.DTO.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.common.Validation.TcKimlikNo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSurveyQuestionDto {
    private String questionName;

    private String answer;

    @TcKimlikNo
    private String tcKimlikNo;
}
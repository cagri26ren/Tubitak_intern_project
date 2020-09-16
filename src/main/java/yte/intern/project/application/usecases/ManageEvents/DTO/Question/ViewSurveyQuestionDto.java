package yte.intern.project.application.usecases.ManageEvents.DTO.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewSurveyQuestionDto{

    private String questionName;

    private String answer;
}

package yte.intern.project.application.usecases.ManageEvents.DTO.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.common.Validation.TcKimlikNo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TcGetSurveyEventDto {

    GetSurveyEventDto getSurveyEventDto;

    @TcKimlikNo
    String tcKimlikNo;
}

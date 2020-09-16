package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AddSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddSurveyQuestionMapper {

    AddSurveyQuestionDto mapToDto(SurveyQuestion surveyQuestion);

    SurveyQuestion mapToEntity( AddSurveyQuestionDto addSurveyQuestionDto);

    List<AddSurveyQuestionDto> mapToDto(List<SurveyQuestion> surveyQuestions);

    List<SurveyQuestion> mapToEntity( List<AddSurveyQuestionDto> addSurveyQuestionDtos);
}


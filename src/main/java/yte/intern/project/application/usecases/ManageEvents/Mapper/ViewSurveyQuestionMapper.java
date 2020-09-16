package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViewSurveyQuestionMapper {

    ViewSurveyQuestionDto mapToDto(SurveyQuestion surveyQuestion);

    SurveyQuestion mapToEntity( ViewSurveyQuestionDto viewSurveyQuestionDto);

    List<ViewSurveyQuestionDto> mapToDto(List<SurveyQuestion> surveyQuestions);

    List<SurveyQuestion> mapToEntity( List<ViewSurveyQuestionDto> viewSurveyQuestionDtos);
}
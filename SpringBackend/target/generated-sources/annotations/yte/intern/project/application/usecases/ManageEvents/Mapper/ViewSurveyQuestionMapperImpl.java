package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:51:49+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class ViewSurveyQuestionMapperImpl implements ViewSurveyQuestionMapper {

    @Override
    public ViewSurveyQuestionDto mapToDto(SurveyQuestion surveyQuestion) {
        if ( surveyQuestion == null ) {
            return null;
        }

        ViewSurveyQuestionDto viewSurveyQuestionDto = new ViewSurveyQuestionDto();

        viewSurveyQuestionDto.setQuestionName( surveyQuestion.getQuestionName() );
        viewSurveyQuestionDto.setAnswer( surveyQuestion.getAnswer() );

        return viewSurveyQuestionDto;
    }

    @Override
    public SurveyQuestion mapToEntity(ViewSurveyQuestionDto viewSurveyQuestionDto) {
        if ( viewSurveyQuestionDto == null ) {
            return null;
        }

        SurveyQuestion surveyQuestion = new SurveyQuestion();

        surveyQuestion.setQuestionName( viewSurveyQuestionDto.getQuestionName() );
        surveyQuestion.setAnswer( viewSurveyQuestionDto.getAnswer() );

        return surveyQuestion;
    }

    @Override
    public List<ViewSurveyQuestionDto> mapToDto(List<SurveyQuestion> surveyQuestions) {
        if ( surveyQuestions == null ) {
            return null;
        }

        List<ViewSurveyQuestionDto> list = new ArrayList<ViewSurveyQuestionDto>( surveyQuestions.size() );
        for ( SurveyQuestion surveyQuestion : surveyQuestions ) {
            list.add( mapToDto( surveyQuestion ) );
        }

        return list;
    }

    @Override
    public List<SurveyQuestion> mapToEntity(List<ViewSurveyQuestionDto> viewSurveyQuestionDtos) {
        if ( viewSurveyQuestionDtos == null ) {
            return null;
        }

        List<SurveyQuestion> list = new ArrayList<SurveyQuestion>( viewSurveyQuestionDtos.size() );
        for ( ViewSurveyQuestionDto viewSurveyQuestionDto : viewSurveyQuestionDtos ) {
            list.add( mapToEntity( viewSurveyQuestionDto ) );
        }

        return list;
    }
}

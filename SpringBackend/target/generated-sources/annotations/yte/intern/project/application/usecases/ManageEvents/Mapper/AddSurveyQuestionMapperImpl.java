package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AddSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:51:49+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class AddSurveyQuestionMapperImpl implements AddSurveyQuestionMapper {

    @Override
    public AddSurveyQuestionDto mapToDto(SurveyQuestion surveyQuestion) {
        if ( surveyQuestion == null ) {
            return null;
        }

        AddSurveyQuestionDto addSurveyQuestionDto = new AddSurveyQuestionDto();

        addSurveyQuestionDto.setQuestionName( surveyQuestion.getQuestionName() );
        addSurveyQuestionDto.setAnswer( surveyQuestion.getAnswer() );

        return addSurveyQuestionDto;
    }

    @Override
    public SurveyQuestion mapToEntity(AddSurveyQuestionDto addSurveyQuestionDto) {
        if ( addSurveyQuestionDto == null ) {
            return null;
        }

        SurveyQuestion surveyQuestion = new SurveyQuestion();

        surveyQuestion.setQuestionName( addSurveyQuestionDto.getQuestionName() );
        surveyQuestion.setAnswer( addSurveyQuestionDto.getAnswer() );

        return surveyQuestion;
    }

    @Override
    public List<AddSurveyQuestionDto> mapToDto(List<SurveyQuestion> surveyQuestions) {
        if ( surveyQuestions == null ) {
            return null;
        }

        List<AddSurveyQuestionDto> list = new ArrayList<AddSurveyQuestionDto>( surveyQuestions.size() );
        for ( SurveyQuestion surveyQuestion : surveyQuestions ) {
            list.add( mapToDto( surveyQuestion ) );
        }

        return list;
    }

    @Override
    public List<SurveyQuestion> mapToEntity(List<AddSurveyQuestionDto> addSurveyQuestionDtos) {
        if ( addSurveyQuestionDtos == null ) {
            return null;
        }

        List<SurveyQuestion> list = new ArrayList<SurveyQuestion>( addSurveyQuestionDtos.size() );
        for ( AddSurveyQuestionDto addSurveyQuestionDto : addSurveyQuestionDtos ) {
            list.add( mapToEntity( addSurveyQuestionDto ) );
        }

        return list;
    }
}

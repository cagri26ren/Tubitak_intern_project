package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.SurveyParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyEventQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:51:49+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class SurveyParticipantMapperImpl implements SurveyParticipantMapper {

    @Override
    public SurveyParticipantDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        SurveyParticipantDto surveyParticipantDto = new SurveyParticipantDto();

        surveyParticipantDto.setName( event.getName() );
        surveyParticipantDto.setSurveyQuestions( surveyEventQuestionSetToViewSurveyQuestionDtoList( event.getSurveyQuestions() ) );

        return surveyParticipantDto;
    }

    @Override
    public Event mapToEntity(SurveyParticipantDto surveyParticipantDto) {
        if ( surveyParticipantDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( surveyParticipantDto.getName() );
        event.setSurveyQuestions( viewSurveyQuestionDtoListToSurveyEventQuestionSet( surveyParticipantDto.getSurveyQuestions() ) );

        return event;
    }

    @Override
    public List<SurveyParticipantDto> mapToDto(List<Event> event) {
        if ( event == null ) {
            return null;
        }

        List<SurveyParticipantDto> list = new ArrayList<SurveyParticipantDto>( event.size() );
        for ( Event event1 : event ) {
            list.add( mapToDto( event1 ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<SurveyParticipantDto> surveyParticipantDtos) {
        if ( surveyParticipantDtos == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( surveyParticipantDtos.size() );
        for ( SurveyParticipantDto surveyParticipantDto : surveyParticipantDtos ) {
            list.add( mapToEntity( surveyParticipantDto ) );
        }

        return list;
    }

    protected ViewSurveyQuestionDto surveyEventQuestionToViewSurveyQuestionDto(SurveyEventQuestion surveyEventQuestion) {
        if ( surveyEventQuestion == null ) {
            return null;
        }

        ViewSurveyQuestionDto viewSurveyQuestionDto = new ViewSurveyQuestionDto();

        return viewSurveyQuestionDto;
    }

    protected List<ViewSurveyQuestionDto> surveyEventQuestionSetToViewSurveyQuestionDtoList(Set<SurveyEventQuestion> set) {
        if ( set == null ) {
            return null;
        }

        List<ViewSurveyQuestionDto> list = new ArrayList<ViewSurveyQuestionDto>( set.size() );
        for ( SurveyEventQuestion surveyEventQuestion : set ) {
            list.add( surveyEventQuestionToViewSurveyQuestionDto( surveyEventQuestion ) );
        }

        return list;
    }

    protected SurveyEventQuestion viewSurveyQuestionDtoToSurveyEventQuestion(ViewSurveyQuestionDto viewSurveyQuestionDto) {
        if ( viewSurveyQuestionDto == null ) {
            return null;
        }

        SurveyEventQuestion surveyEventQuestion = new SurveyEventQuestion();

        return surveyEventQuestion;
    }

    protected Set<SurveyEventQuestion> viewSurveyQuestionDtoListToSurveyEventQuestionSet(List<ViewSurveyQuestionDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<SurveyEventQuestion> set = new HashSet<SurveyEventQuestion>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ViewSurveyQuestionDto viewSurveyQuestionDto : list ) {
            set.add( viewSurveyQuestionDtoToSurveyEventQuestion( viewSurveyQuestionDto ) );
        }

        return set;
    }
}

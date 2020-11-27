package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetSurveyEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.SurveyParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-07T16:16:23+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class GetSurveyEventMapperImpl implements GetSurveyEventMapper {

    @Override
    public GetSurveyEventDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        GetSurveyEventDto getSurveyEventDto = new GetSurveyEventDto();

        getSurveyEventDto.setName( event.getName() );
        getSurveyEventDto.setStartDate( event.getStartDate() );
        getSurveyEventDto.setStartTime( event.getStartTime() );
        getSurveyEventDto.setEndDate( event.getEndDate() );
        getSurveyEventDto.setEndTime( event.getEndTime() );
        getSurveyEventDto.setParticipants( participantSetToSurveyParticipantDtoList( event.getParticipants() ) );

        return getSurveyEventDto;
    }

    @Override
    public Event mapToEntity(GetSurveyEventDto getSurveyEventDto) {
        if ( getSurveyEventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( getSurveyEventDto.getName() );
        event.setStartDate( getSurveyEventDto.getStartDate() );
        event.setEndDate( getSurveyEventDto.getEndDate() );
        event.setStartTime( getSurveyEventDto.getStartTime() );
        event.setEndTime( getSurveyEventDto.getEndTime() );
        event.setParticipants( surveyParticipantDtoListToParticipantSet( getSurveyEventDto.getParticipants() ) );

        return event;
    }

    @Override
    public List<GetSurveyEventDto> mapToDto(List<Event> event) {
        if ( event == null ) {
            return null;
        }

        List<GetSurveyEventDto> list = new ArrayList<GetSurveyEventDto>( event.size() );
        for ( Event event1 : event ) {
            list.add( mapToDto( event1 ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<GetSurveyEventDto> getSurveyEventDtos) {
        if ( getSurveyEventDtos == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( getSurveyEventDtos.size() );
        for ( GetSurveyEventDto getSurveyEventDto : getSurveyEventDtos ) {
            list.add( mapToEntity( getSurveyEventDto ) );
        }

        return list;
    }

    protected ViewSurveyQuestionDto surveyQuestionToViewSurveyQuestionDto(SurveyQuestion surveyQuestion) {
        if ( surveyQuestion == null ) {
            return null;
        }

        ViewSurveyQuestionDto viewSurveyQuestionDto = new ViewSurveyQuestionDto();

        viewSurveyQuestionDto.setQuestionName( surveyQuestion.getQuestionName() );
        viewSurveyQuestionDto.setAnswer( surveyQuestion.getAnswer() );

        return viewSurveyQuestionDto;
    }

    protected List<ViewSurveyQuestionDto> surveyQuestionSetToViewSurveyQuestionDtoList(Set<SurveyQuestion> set) {
        if ( set == null ) {
            return null;
        }

        List<ViewSurveyQuestionDto> list = new ArrayList<ViewSurveyQuestionDto>( set.size() );
        for ( SurveyQuestion surveyQuestion : set ) {
            list.add( surveyQuestionToViewSurveyQuestionDto( surveyQuestion ) );
        }

        return list;
    }

    protected SurveyParticipantDto participantToSurveyParticipantDto(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        SurveyParticipantDto surveyParticipantDto = new SurveyParticipantDto();

        surveyParticipantDto.setName( participant.getName() );
        surveyParticipantDto.setSurname( participant.getSurname() );
        surveyParticipantDto.setEmail( participant.getEmail() );
        surveyParticipantDto.setTcKimlikNo( participant.getTcKimlikNo() );
        surveyParticipantDto.setSurveyQuestions( surveyQuestionSetToViewSurveyQuestionDtoList( participant.getSurveyQuestions() ) );

        return surveyParticipantDto;
    }

    protected List<SurveyParticipantDto> participantSetToSurveyParticipantDtoList(Set<Participant> set) {
        if ( set == null ) {
            return null;
        }

        List<SurveyParticipantDto> list = new ArrayList<SurveyParticipantDto>( set.size() );
        for ( Participant participant : set ) {
            list.add( participantToSurveyParticipantDto( participant ) );
        }

        return list;
    }

    protected SurveyQuestion viewSurveyQuestionDtoToSurveyQuestion(ViewSurveyQuestionDto viewSurveyQuestionDto) {
        if ( viewSurveyQuestionDto == null ) {
            return null;
        }

        SurveyQuestion surveyQuestion = new SurveyQuestion();

        surveyQuestion.setQuestionName( viewSurveyQuestionDto.getQuestionName() );
        surveyQuestion.setAnswer( viewSurveyQuestionDto.getAnswer() );

        return surveyQuestion;
    }

    protected Set<SurveyQuestion> viewSurveyQuestionDtoListToSurveyQuestionSet(List<ViewSurveyQuestionDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<SurveyQuestion> set = new HashSet<SurveyQuestion>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ViewSurveyQuestionDto viewSurveyQuestionDto : list ) {
            set.add( viewSurveyQuestionDtoToSurveyQuestion( viewSurveyQuestionDto ) );
        }

        return set;
    }

    protected Participant surveyParticipantDtoToParticipant(SurveyParticipantDto surveyParticipantDto) {
        if ( surveyParticipantDto == null ) {
            return null;
        }

        Participant participant = new Participant();

        participant.setName( surveyParticipantDto.getName() );
        participant.setSurname( surveyParticipantDto.getSurname() );
        participant.setEmail( surveyParticipantDto.getEmail() );
        participant.setTcKimlikNo( surveyParticipantDto.getTcKimlikNo() );
        participant.setSurveyQuestions( viewSurveyQuestionDtoListToSurveyQuestionSet( surveyParticipantDto.getSurveyQuestions() ) );

        return participant;
    }

    protected Set<Participant> surveyParticipantDtoListToParticipantSet(List<SurveyParticipantDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Participant> set = new HashSet<Participant>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( SurveyParticipantDto surveyParticipantDto : list ) {
            set.add( surveyParticipantDtoToParticipant( surveyParticipantDto ) );
        }

        return set;
    }
}

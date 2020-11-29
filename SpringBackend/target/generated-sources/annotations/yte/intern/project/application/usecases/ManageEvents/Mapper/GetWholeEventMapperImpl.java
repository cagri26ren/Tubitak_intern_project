package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetWholeEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.AddParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AnswerDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Answer;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-29T18:08:13+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class GetWholeEventMapperImpl implements GetWholeEventMapper {

    @Override
    public GetWholeEventDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        GetWholeEventDto getWholeEventDto = new GetWholeEventDto();

        getWholeEventDto.setName( event.getName() );
        getWholeEventDto.setMax( event.getMax() );
        getWholeEventDto.setCurrent( event.getCurrent() );
        getWholeEventDto.setStartDate( event.getStartDate() );
        getWholeEventDto.setStartTime( event.getStartTime() );
        getWholeEventDto.setEndDate( event.getEndDate() );
        getWholeEventDto.setEndTime( event.getEndTime() );
        getWholeEventDto.setLat( event.getLat() );
        getWholeEventDto.setLng( event.getLng() );
        getWholeEventDto.setParticipants( participantSetToAddParticipantDtoList( event.getParticipants() ) );

        return getWholeEventDto;
    }

    @Override
    public Event mapToEntity(GetWholeEventDto getWholeEventDto) {
        if ( getWholeEventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( getWholeEventDto.getName() );
        event.setMax( getWholeEventDto.getMax() );
        event.setCurrent( getWholeEventDto.getCurrent() );
        event.setStartDate( getWholeEventDto.getStartDate() );
        event.setEndDate( getWholeEventDto.getEndDate() );
        event.setStartTime( getWholeEventDto.getStartTime() );
        event.setEndTime( getWholeEventDto.getEndTime() );
        event.setLat( getWholeEventDto.getLat() );
        event.setLng( getWholeEventDto.getLng() );
        event.setParticipants( addParticipantDtoListToParticipantSet( getWholeEventDto.getParticipants() ) );

        return event;
    }

    @Override
    public List<GetWholeEventDto> mapToDto(List<Event> event) {
        if ( event == null ) {
            return null;
        }

        List<GetWholeEventDto> list = new ArrayList<GetWholeEventDto>( event.size() );
        for ( Event event1 : event ) {
            list.add( mapToDto( event1 ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<GetWholeEventDto> getWholeEventDtos) {
        if ( getWholeEventDtos == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( getWholeEventDtos.size() );
        for ( GetWholeEventDto getWholeEventDto : getWholeEventDtos ) {
            list.add( mapToEntity( getWholeEventDto ) );
        }

        return list;
    }

    protected AnswerDto answerToAnswerDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto answerDto = new AnswerDto();

        answerDto.setQuestionName( answer.getQuestionName() );
        answerDto.setAnswer( answer.getAnswer() );

        return answerDto;
    }

    protected List<AnswerDto> answerSetToAnswerDtoList(Set<Answer> set) {
        if ( set == null ) {
            return null;
        }

        List<AnswerDto> list = new ArrayList<AnswerDto>( set.size() );
        for ( Answer answer : set ) {
            list.add( answerToAnswerDto( answer ) );
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

    protected AddParticipantDto participantToAddParticipantDto(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        AddParticipantDto addParticipantDto = new AddParticipantDto();

        addParticipantDto.setName( participant.getName() );
        addParticipantDto.setSurname( participant.getSurname() );
        addParticipantDto.setEmail( participant.getEmail() );
        addParticipantDto.setTcKimlikNo( participant.getTcKimlikNo() );
        addParticipantDto.setJoinDate( participant.getJoinDate() );
        addParticipantDto.setJoinTime( participant.getJoinTime() );
        addParticipantDto.setAge( participant.getAge() );
        addParticipantDto.setGender( participant.getGender() );
        addParticipantDto.setAnswers( answerSetToAnswerDtoList( participant.getAnswers() ) );
        addParticipantDto.setSurveyQuestions( surveyQuestionSetToViewSurveyQuestionDtoList( participant.getSurveyQuestions() ) );

        return addParticipantDto;
    }

    protected List<AddParticipantDto> participantSetToAddParticipantDtoList(Set<Participant> set) {
        if ( set == null ) {
            return null;
        }

        List<AddParticipantDto> list = new ArrayList<AddParticipantDto>( set.size() );
        for ( Participant participant : set ) {
            list.add( participantToAddParticipantDto( participant ) );
        }

        return list;
    }

    protected Answer answerDtoToAnswer(AnswerDto answerDto) {
        if ( answerDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setQuestionName( answerDto.getQuestionName() );
        answer.setAnswer( answerDto.getAnswer() );

        return answer;
    }

    protected Set<Answer> answerDtoListToAnswerSet(List<AnswerDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Answer> set = new HashSet<Answer>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( AnswerDto answerDto : list ) {
            set.add( answerDtoToAnswer( answerDto ) );
        }

        return set;
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

    protected Participant addParticipantDtoToParticipant(AddParticipantDto addParticipantDto) {
        if ( addParticipantDto == null ) {
            return null;
        }

        Participant participant = new Participant();

        participant.setName( addParticipantDto.getName() );
        participant.setSurname( addParticipantDto.getSurname() );
        participant.setEmail( addParticipantDto.getEmail() );
        participant.setTcKimlikNo( addParticipantDto.getTcKimlikNo() );
        participant.setJoinDate( addParticipantDto.getJoinDate() );
        participant.setJoinTime( addParticipantDto.getJoinTime() );
        participant.setAge( addParticipantDto.getAge() );
        participant.setGender( addParticipantDto.getGender() );
        participant.setAnswers( answerDtoListToAnswerSet( addParticipantDto.getAnswers() ) );
        participant.setSurveyQuestions( viewSurveyQuestionDtoListToSurveyQuestionSet( addParticipantDto.getSurveyQuestions() ) );

        return participant;
    }

    protected Set<Participant> addParticipantDtoListToParticipantSet(List<AddParticipantDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Participant> set = new HashSet<Participant>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( AddParticipantDto addParticipantDto : list ) {
            set.add( addParticipantDtoToParticipant( addParticipantDto ) );
        }

        return set;
    }
}

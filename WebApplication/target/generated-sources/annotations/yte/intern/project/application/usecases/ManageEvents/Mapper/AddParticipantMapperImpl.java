package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.AddParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AnswerDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Answer;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-07T16:16:23+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class AddParticipantMapperImpl implements AddParticipantMapper {

    @Override
    public AddParticipantDto mapToDto(Participant participant) {
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

    @Override
    public Participant mapToEntity(AddParticipantDto addParticipantDto) {
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

    @Override
    public List<AddParticipantDto> mapToDto(List<Participant> participants) {
        if ( participants == null ) {
            return null;
        }

        List<AddParticipantDto> list = new ArrayList<AddParticipantDto>( participants.size() );
        for ( Participant participant : participants ) {
            list.add( mapToDto( participant ) );
        }

        return list;
    }

    @Override
    public List<Participant> mapToEntity(List<AddParticipantDto> addParticipantDtos) {
        if ( addParticipantDtos == null ) {
            return null;
        }

        List<Participant> list = new ArrayList<Participant>( addParticipantDtos.size() );
        for ( AddParticipantDto addParticipantDto : addParticipantDtos ) {
            list.add( mapToEntity( addParticipantDto ) );
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
}
